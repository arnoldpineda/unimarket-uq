package co.edu.uniquindio.unimarket.servicios.implementacion;

import co.edu.uniquindio.unimarket.dto.ProductoDTO;
import co.edu.uniquindio.unimarket.dto.ProductoGetDTO;
import co.edu.uniquindio.unimarket.entidades.Categoria;
import co.edu.uniquindio.unimarket.entidades.Estado;
import co.edu.uniquindio.unimarket.entidades.Producto;
import co.edu.uniquindio.unimarket.entidades.Usuario;
import co.edu.uniquindio.unimarket.repositorios.ProductoRepo;
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
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepo;

    private final UsuarioServicio usuarioServicio;


    @Override
    public int crearProducto(ProductoDTO productoDTO) throws Exception {
        Producto producto = new Producto();
        producto.setNombre( productoDTO.getNombre() );
        producto.setDescripcion( productoDTO.getDescripcion() );
        producto.setUnidades( productoDTO.getUnidades() );
        producto.setPrecio( productoDTO.getPrecio() );
        producto.setVendedor( usuarioServicio.obtener( productoDTO.getCodigoVendedor() ) );
        producto.setImagen( productoDTO.getImagenes() );
        producto.setCategoria( productoDTO.getCategorias() );
        producto.setActivo(false); // Se crea como falso es decir inactivo, se puede cambiar por una enumeracion
        producto.setFechaCreado( LocalDateTime.now() );
        producto.setFechaLimite( LocalDateTime.now().plusDays(60) ); //Fecha limite es 3 meses despues de crearce

        return productoRepo.save( producto ).getCodigo();
    }

    @Override
    public ProductoGetDTO actualizarProducto(int codigoProducto, ProductoDTO productoDTO) throws Exception{
        validarProductoExiste(codigoProducto); //validar que el codigo del producto exista

        Producto producto = convertirDTO(productoDTO);
        producto.setCodigo(codigoProducto);
        return convertir(productoRepo.save(producto));
    }


    @Override
    public int actualizarUnidades(int codigoProducto, int unidades) throws Exception {
        validarProductoExiste(codigoProducto); //validar que el codigo del producto exista

        Producto producto = obtener(codigoProducto);
        producto.setUnidades(unidades);

        return producto.getUnidades();
    }

    @Override
    public int actualizarEstado(int codigoProducto, Estado estado) throws Exception {
        validarProductoExiste(codigoProducto); //validar que el codigo del producto exista

        Producto producto = obtener(codigoProducto);
        //producto.setActivo(estado);
        return 0;
    }


    @Override
    public int eliminarProducto(int codigoProducto) throws Exception {
        validarProductoExiste(codigoProducto); //validar que el codigo del producto exista
        productoRepo.deleteById(codigoProducto);
        return codigoProducto;
    }

    @Override
    public ProductoGetDTO obtenerProducto(int codigoProducto) throws Exception {

        //obtiene un producto y luego lo convierte en un Producto DTO y lo retorna
        return convertir(obtener(codigoProducto));
    }

    @Override
    public Producto obtener(int codigoProducto) throws Exception {
        //Devuelve un producto dado su codigo
        Optional<Producto> producto = productoRepo.findById(codigoProducto);

        if(producto.isEmpty()){
            throw new Exception("El código "+codigoProducto+" no está asociado a ningún producto");
        }
        return producto.get();
    }

    @Override
    public List<ProductoGetDTO> listarProductosUsuario(int codigoUsuario) throws Exception {
        List<Producto> lista = productoRepo.listarProductosUsuario(codigoUsuario);

        if(lista.isEmpty()){
            throw new Exception("El usuario no tiene productos");
        }

        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for(Producto p : lista){
            respuesta.add( convertir(p) );
        }

        return respuesta;
    }

    @Override
    public List<ProductoGetDTO> listarProductosCategoria(Categoria categoria) throws Exception {
        List<Producto> lista = productoRepo.listarProductoCategoria(categoria);

        if(lista.isEmpty()) {
            throw new Exception("No hay producto en la categoria " + categoria);
        }

        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for(Producto p : lista){
            respuesta.add( convertir(p) );
        }

        return respuesta;
    }

    @Override
    public List<ProductoGetDTO> listarProductosPorEstado(Estado estado) {
        return null;
    }

    @Override
    public List<ProductoGetDTO> listarProductosFavoritos(int codigoUsuario) throws Exception {
        List<Producto> lista = productoRepo.listarFavoritos(codigoUsuario);

        if(lista.isEmpty()){
            throw new Exception("El usuario no tiene productos favoritos");
        }

        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for(Producto p : lista){
            respuesta.add( convertir(p) );
        }

        return respuesta;
    }

    @Override
    public List<ProductoGetDTO> listarProductosNombre(String nombre) throws Exception {
        List<Producto> lista = productoRepo.listarProductosNombre(nombre);

        if(lista.isEmpty()){
            throw new Exception("El producto no esta a la venta");
        }

        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for(Producto p : lista){
            respuesta.add( convertir(p) );
        }
        return respuesta;
    }

    @Override
    public List<ProductoGetDTO> listarProductosPrecio(float precioMinimo, float precioMaximo) throws Exception {
        List<Producto> lista = productoRepo.listarPorPrecio(precioMinimo, precioMaximo);

        if(lista.isEmpty()){
            throw new Exception("No hay productos en ese rango de precios");
        }

        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for(Producto p : lista){
            respuesta.add( convertir(p) );
        }
        return respuesta;
    }

    @Override
    public void crearFavorito(int codigoUsuario, int codigoProducto) throws Exception{
        Usuario usuario = usuarioServicio.obtener(codigoUsuario);
        Producto producto = obtener(codigoProducto);
        usuario.getFavoritos().add(producto);
    }

    @Override
    public void eliminarFavorito(int codigoUsuario, int codigoProducto) throws Exception{
        Usuario usuario = usuarioServicio.obtener(codigoUsuario);
        Producto producto = obtener(codigoProducto);
        usuario.getFavoritos().remove(producto);
    }

    private ProductoGetDTO convertir(Producto producto){
        ProductoGetDTO productoGetDTO = new ProductoGetDTO(
                producto.getCodigo(),
                producto.isActivo(),//preguntar getActivo no sale
                producto.getFechaLimite(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getUnidades(),
                producto.getPrecio(),
                producto.getVendedor().getCodigo(),
                producto.getImagen(),
                producto.getCategoria()
        );
        return productoGetDTO;
    }

    private Producto convertirDTO(ProductoDTO productoDTO) throws Exception {
        Producto producto = new Producto();
            //producto.setActivo(productoDTO.isActivo());
            producto.setNombre(productoDTO.getNombre());
            producto.setDescripcion(productoDTO.getDescripcion());
            producto.setUnidades(productoDTO.getUnidades());
            producto.setPrecio(productoDTO.getPrecio());
            producto.setVendedor(usuarioServicio.obtener(productoDTO.getCodigoVendedor()));
        return producto;
    }

    private void validarProductoExiste (int codigoProducto) throws Exception {
        boolean existe = productoRepo.existsById(codigoProducto);

        if( !existe ){
            throw new Exception("El código "+codigoProducto+" no está asociado a ningún producto");
        }

    }
}
