package co.edu.uniquindio.unimarket.servicios.implementacion;

import co.edu.uniquindio.unimarket.dto.CompraDTO;
import co.edu.uniquindio.unimarket.dto.CompraGetDTO;
import co.edu.uniquindio.unimarket.dto.DetalleCompraDTO;
import co.edu.uniquindio.unimarket.dto.EmailDTO;
import co.edu.uniquindio.unimarket.entidades.Compra;
import co.edu.uniquindio.unimarket.entidades.DetalleCompra;
import co.edu.uniquindio.unimarket.entidades.Producto;
import co.edu.uniquindio.unimarket.repositorios.CompraRepo;
import co.edu.uniquindio.unimarket.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.unimarket.servicios.excepcion.AttributeException;
import co.edu.uniquindio.unimarket.servicios.excepcion.ListaVaciaException;
import co.edu.uniquindio.unimarket.servicios.excepcion.ObjetoNoEncontradoException;
import co.edu.uniquindio.unimarket.servicios.interfaces.CompraServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.ProductoServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompraServicioImpl implements CompraServicio {

    private final CompraRepo compraRepo;
    private final UsuarioServicio usuarioServicio;
    private final ProductoServicio productoServicio;
    private final DetalleCompraRepo detalleCompraRepo;

    private EmailServicio emailServicio;


    @Override
    public int crearCompra(CompraDTO compraDTO) throws Exception {

        float total = 0;
        ArrayList<DetalleCompra> detalleCompras = new ArrayList<>(); //Se crea una lista detalle compra

        for( DetalleCompraDTO detalle: compraDTO.getDetalleCompraDTO() ){
            validarUniDisponibles(detalle.getUnidades(), detalle.getCodigoProducto());
            DetalleCompra dt = crearDetalleCompra(detalle);  // Se crea el detalle
            detalleCompras.add(dt);  //Se guarda el detalle en la lista de detalleCompra
            total += detalle.getUnidades()*detalle.getPrecio();  // Se multiplica el valor por las unidades de cada detalle y se van sumando para sacar el total
        }

        Compra compra = new Compra ();
        compra.setUsuario(usuarioServicio.obtener(compraDTO.getCodigoUsuario())); //asigna el comprador
        compra.setFechaCreacion(LocalDateTime.now());
        compra.setMetodoPago(compraDTO.getMetodoPago());
        compra.setValorTotal(total);
        compra.setDetalleCompras(detalleCompras); //Se asigna la lista que se creo en la funciona anterior

        Compra registro = compraRepo.save(compra); //Se guarda la compra

        for(DetalleCompra dc : detalleCompras){   //A cada detalle de la compra se le asigna la compra
            dc.setCompra(registro);
            detalleCompraRepo.save(dc);

            //Email para el vendedor
            emailServicio.enviarEmail(new EmailDTO("Venta exitosa",dc.toString(), dc.getProducto().getVendedor().getEmail()));
        }

        // Email para el comprador
        emailServicio.enviarEmail(new EmailDTO("Compra exitosa",registro.toString(), registro.getUsuario().getEmail()));

        return registro.getCodigo();  //Retorna la compra
    }

    @Override
    public DetalleCompra crearDetalleCompra(DetalleCompraDTO detalleCompraDTO) throws Exception {

        Producto producto = productoServicio.obtener(detalleCompraDTO.getCodigoProducto()); //se guarda el producto

        DetalleCompra detalleCompra = new DetalleCompra();
        detalleCompra.setUnidades(detalleCompraDTO.getUnidades());
        detalleCompra.setProducto(producto); ////se asigna el producto guardado
        detalleCompra.setPrecioProducto(producto.getPrecio()); //Se adigna el valor unitario del producto

        return detalleCompra;
    }

    @Override
    public List<CompraGetDTO> listarCompras(int codigoUsuario) throws Exception {
        List<Compra> lista = compraRepo.listarComprasUsuario(codigoUsuario);

        if(lista.isEmpty()){
            throw new ListaVaciaException("El usuario no ha realizado compras");
        }

        List<CompraGetDTO> respuesta = new ArrayList<>();

        for (Compra c : lista){
            respuesta.add(convertir(c));
        }

        return respuesta;
    }

    @Override
    public Compra obtenerCompra(int codigoCompra) throws Exception{
        //Devuelve una compra dado su codigo
        Optional<Compra> compra = compraRepo.findById(codigoCompra);

        if(compra.isEmpty()){
            throw new ObjetoNoEncontradoException("El código "+codigoCompra+" no está asociado a ningúna compra");
        }
        return compra.get();
    }


    private void validarUniDisponibles (int canSeleccionada, int codigoProducto) throws Exception {

        if (canSeleccionada >= productoServicio.obtener(codigoProducto).getUnidades()){
            throw new AttributeException("La cantidad seleccionada es superior a las unidades disponibles");
        }
    }


    private CompraGetDTO convertir(Compra compra) {

        List<DetalleCompra> lista = compra.getDetalleCompras(); //guarda las lista de detalle compra

        ArrayList<DetalleCompraDTO> listaDTO = new ArrayList<>(); //Crea una lista de DetalleCompraDTO

        //Cada detalleCompra que tiene la compra lo convierte en DTO y lo guarda en la lista detalleCompraDTO
        for(DetalleCompra dc: lista){
            listaDTO.add(convertirDc(dc));
        }

        CompraGetDTO compraGetDTO = new CompraGetDTO(
                compra.getCodigo(),
                compra.getFechaCreacion(),
                compra.getValorTotal(),
                compra.getUsuario().getCodigo(),
                compra.getMetodoPago(),
                listaDTO
        );
        return compraGetDTO;
    }

    private DetalleCompraDTO convertirDc(DetalleCompra detalleCompra){

        DetalleCompraDTO detalleCompraDTO = new DetalleCompraDTO(
                detalleCompra.getCodigo(),
                detalleCompra.getUnidades(),
                detalleCompra.getPrecioProducto()
        );

        return detalleCompraDTO;
    }
}
