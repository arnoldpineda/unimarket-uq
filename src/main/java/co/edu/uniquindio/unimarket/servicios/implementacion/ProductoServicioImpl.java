package co.edu.uniquindio.unimarket.servicios.implementacion;

import co.edu.uniquindio.unimarket.dto.ProductoDTO;
import co.edu.uniquindio.unimarket.dto.ProductoGetDTO;
import co.edu.uniquindio.unimarket.entidades.Categoria;
import co.edu.uniquindio.unimarket.entidades.Estado;
import co.edu.uniquindio.unimarket.entidades.Producto;
import co.edu.uniquindio.unimarket.entidades.Usuario;
import co.edu.uniquindio.unimarket.repositorios.ProductoRepo;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniquindio.unimarket.servicios.excepcion.ListaVaciaException;
import co.edu.uniquindio.unimarket.servicios.excepcion.ObjetoNoEncontradoException;
import co.edu.uniquindio.unimarket.servicios.interfaces.ProductoServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepo;

    private final UsuarioServicio usuarioServicio;


    @Override
    public int crearProducto(ProductoDTO productoDTO) throws Exception {

        Producto producto = convertirDTO(productoDTO);
        return productoRepo.save( producto ).getCodigo();
    }

    @Override
    public ProductoGetDTO actualizarProducto(int codigoProducto, ProductoDTO productoDTO) throws Exception{
        validarProductoExiste(codigoProducto); //validar que el codigo del producto exista

        Producto producto = convertirDTO(productoDTO);
        producto.setCodigo(codigoProducto);
        return convertir(productoRepo.save(producto));
    }

    //Actualiza las unidades al realizar una compra
    @Override
    public int actualizarUnidades(int codigoProducto, int unidades) throws Exception {
        validarProductoExiste(codigoProducto); //validar que el codigo del producto exista

        Producto producto = obtener(codigoProducto);
        producto.setUnidades(unidades);

        return producto.getUnidades();
    }

    //Cambia el estado de un producto cuando el moderador lo revisa
    @Override
    public int actualizarEstado(int codigoProducto, Estado estado) throws Exception {
        validarProductoExiste(codigoProducto); //validar que el codigo del producto exista

        Producto producto = obtener(codigoProducto);
        producto.setActivo(estado);
        return codigoProducto;
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
            throw new ObjetoNoEncontradoException("El código "+codigoProducto+" no está asociado a ningún producto");
        }
        return producto.get();
    }

    @Override
    public List<ProductoGetDTO> listarProductosUsuario(int codigoUsuario) throws Exception {
        List<Producto> lista = productoRepo.listarProductosUsuario(codigoUsuario);

        if(lista.isEmpty()){
            throw new ListaVaciaException("El usuario no tiene productos");
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
            throw new ObjetoNoEncontradoException("No hay producto en la categoria " + categoria);
        }

        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for(Producto p : lista){
            respuesta.add( convertir(p) );
        }

        return respuesta;
    }

    //Lista si los producto autorizados, Denegados Sin revisar, los Autorizados son los que se deben mostrar en la pag inicio
    @Override
    public List<ProductoGetDTO> listarProductosPorEstado(Estado estado) throws Exception {
        List<Producto> lista = productoRepo.listarProductosEstado(estado);

        if(lista.isEmpty()) {
            throw new ObjetoNoEncontradoException("No hay productos con estado " + estado);
        }

        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for(Producto p : lista){
            respuesta.add( convertir(p) );
        }
        return respuesta;
    }

    @Override
    public List<ProductoGetDTO> listarProductosEstadoModerador(int codigoModerador, Estado estado) throws Exception {
        List<Producto> lista = productoRepo.listarProductosEstadoModerador(codigoModerador, estado);

        if(lista.isEmpty()) {
            throw new ListaVaciaException("Usted no tiene productos con estado " + estado);
        }

        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for(Producto p : lista){
            respuesta.add( convertir(p) );
        }
        return respuesta;
    }

    @Override
    public List<ProductoGetDTO> listarProductosFavoritos(int codigoUsuario) throws Exception {
        List<Producto> lista = productoRepo.listarFavoritos(codigoUsuario);

        if(lista.isEmpty()){
            throw new ListaVaciaException("El usuario no tiene productos favoritos");
        }

        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for(Producto p : lista){
            respuesta.add( convertir(p) );
        }

        return respuesta;
    }

    @Override
    public List<ProductoGetDTO> listarProductosDisponibles() throws Exception{
        List<Producto> lista = productoRepo.listarProductosDisponibles();

        if(lista.isEmpty()){
            throw new ListaVaciaException("No hay productos disponibles para la venta");
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
            throw new ListaVaciaException("El producto no esta a la venta");
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
            throw new ListaVaciaException("No hay productos en ese rango de precios");
        }

        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for(Producto p : lista){
            respuesta.add( convertir(p) );
        }
        return respuesta;
    }


    @Transactional
    @Override
    public void agregarFavorito(int codigoUsuario, int codigoProducto) throws Exception{
        Producto producto = obtener(codigoProducto);
        Usuario usuario = usuarioServicio.obtener(codigoUsuario);
        usuario.getFavoritos().add(producto);
    }


    @Transactional
    @Override
    public void eliminarFavorito(int codigoUsuario, int codigoProducto) throws Exception{

        Producto producto = obtener(codigoProducto);
        Usuario usuario = usuarioServicio.obtener(codigoUsuario);
        usuario.getFavoritos().remove(producto);
    }

    private ProductoGetDTO convertir(Producto producto){
        ProductoGetDTO productoGetDTO = new ProductoGetDTO(
                producto.getCodigo(),
                producto.getActivo(),
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

        Map<String, String> mapa = new HashMap<>();
        productoDTO.getImagenes().forEach( i ->
                mapa.put(i.getKey(), i.getValue()))
        ;

        Producto producto = new Producto();
            producto.setNombre(productoDTO.getNombre());
            producto.setDescripcion(productoDTO.getDescripcion());
            producto.setUnidades(productoDTO.getUnidades());
            producto.setPrecio(productoDTO.getPrecio());
            producto.setVendedor(usuarioServicio.obtener(productoDTO.getCodigoVendedor()));
            producto.setImagen(mapa);
            producto.setCategoria(productoDTO.getCategorias());
            producto.setActivo(Estado.SIN_REVISAR); // Se crea como SIN_REVISAR es decir inactivo
            producto.setFechaCreado( LocalDateTime.now() );
            producto.setFechaLimite( LocalDateTime.now().plusDays(60) ); //Fecha limite es 3 meses

        return producto;
    }

    private void validarProductoExiste (int codigoProducto) throws Exception {
        boolean existe = productoRepo.existsById(codigoProducto);

        if( !existe ){
            throw new ObjetoNoEncontradoException("El código "+codigoProducto+" no está asociado a ningún producto");
        }
    }

}
