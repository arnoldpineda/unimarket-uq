package co.edu.uniquindio.unimarket.servicios.implementacion;

import co.edu.uniquindio.unimarket.dto.EmailDTO;
import co.edu.uniquindio.unimarket.dto.ProductoModeradorDTO;
import co.edu.uniquindio.unimarket.entidades.Producto;
import co.edu.uniquindio.unimarket.entidades.ProductoModerador;
import co.edu.uniquindio.unimarket.repositorios.ProductoModeradorRepo;
import co.edu.uniquindio.unimarket.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.ModeradorServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.ProductoModeradorServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.ProductoServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ProductoModeradorImpl implements ProductoModeradorServicio {

    private final ProductoModeradorRepo productoModeradorRepo;
    private final ProductoServicio productoServicio;
    private final ModeradorServicio moderadorServicio;
    private EmailServicio emailServicio;

    @Override
    public void registrarCambioEstado(ProductoModeradorDTO productoModeradorDTO) throws Exception {

        Producto producto = productoServicio.obtener(productoModeradorDTO.getCodigoProducto()); //guarda el producto

        ProductoModerador productoModerador = new ProductoModerador();
        productoModerador.setMotivo(productoModeradorDTO.getMotivo());
        productoModerador.setProducto(producto); //asigna el producto
        productoModerador.setModerador(moderadorServicio.obtener(productoModeradorDTO.getCodigoModerador()));
        productoModerador.setEstado(productoModeradorDTO.getEstado());
        productoModerador.setFecha(LocalDateTime.now());

        //Actualiza el estado del producto
        productoServicio.actualizarEstado(productoModeradorDTO.getCodigoProducto(), productoModeradorDTO.getEstado());

        productoModeradorRepo.save(productoModerador);

        //envia cprreo
        emailServicio.enviarEmail(new EmailDTO(
                "Producto " + producto.getNombre() + " " + productoModeradorDTO.getEstado() ,
                "El producto: " + producto.getNombre() + " fue " + productoModeradorDTO.getEstado() + " Motivo: " + productoModeradorDTO.getMotivo(),
                producto.getVendedor().getEmail()
        ));

    }

}
