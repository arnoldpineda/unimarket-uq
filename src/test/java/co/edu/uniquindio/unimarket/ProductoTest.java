package co.edu.uniquindio.unimarket;

import co.edu.uniquindio.unimarket.dto.ProductoDTO;
import co.edu.uniquindio.unimarket.dto.ProductoGetDTO;
import co.edu.uniquindio.unimarket.entidades.Categoria;
import co.edu.uniquindio.unimarket.entidades.Estado;
import co.edu.uniquindio.unimarket.entidades.Producto;
import co.edu.uniquindio.unimarket.servicios.interfaces.ProductoServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.UsuarioServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Transactional
public class ProductoTest {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarProducto()throws Exception{

        /*UsuarioDTO usuarioDTO = new UsuarioDTO(
                "Pepito 1",
                "pepe1@email.com",
                "1234",
                "Calle 123",
                "343");

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int codigoVendedor = usuarioServicio.crearUsuario(usuarioDTO);
        */
        // Se obtiene un codigo de la base de datos
        int codigoVendedor = 1;

        //Se crea el mapa de imágenes para el producto.
        Map<String, String> imagenes = new HashMap<>();
        imagenes.put("http://www.google.com/images/imagenasus.png","1");

        //Se crea el producto y se asigna el codigo vendedor registrado en la BD
        ProductoDTO productoDTO = new ProductoDTO(
                "Computador Asus 1",
                "Es el mejor computador portatil que el dinero pueda comprar",
                5,
                7000000,
                codigoVendedor,
                imagenes,
                List.of(Categoria.DEPORTES)
        );

        //Se llama el servicio para crear el producto
        int codigoProducto = productoServicio.crearProducto( productoDTO );

        //Se espera que el servicio retorne el código del nuevo producto
        Assertions.assertNotEquals(0, codigoProducto);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarServicioTest () throws Exception{

        Map<String, String> imagenes = new HashMap<>();
        imagenes.put("http://www.google.com/images/imagenasus.png","1");

        ProductoGetDTO productoActualizado = productoServicio.actualizarProducto(1, new ProductoDTO("camisa", "camisa de hombre", 13,25000, 1, imagenes, List.of(Categoria.MODA))) ;

        //Se comprueba que ahora el nombre del producto no es el mismo inicial
        Assertions.assertNotEquals("Blusa", productoActualizado.getNombre());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerTest()throws Exception{

        Producto producto = (productoServicio.obtener(2));
        //Assertions.assertNotEquals(0, producto.getCodigo());
       Assertions.assertNotNull(producto);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerProductoTest() throws Exception {
        ProductoGetDTO productoGetDTO = productoServicio.obtenerProducto(1);
        Assertions.assertNotNull(productoGetDTO);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarUnidadesTest () throws Exception{

        int unidades = productoServicio.actualizarUnidades(1, 50);
        Assertions.assertEquals(50, unidades);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarEstadoTest()throws Exception{
        int codigoProducto = productoServicio.actualizarEstado(3,Estado.AUTORIZADO);
        Assertions.assertEquals(Estado.AUTORIZADO, productoServicio.obtenerProducto(3).getActivo());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarProductoTest() throws Exception{
        int codigoBorrado = productoServicio.eliminarProducto(1);

        //Si intentamos buscar un usuario con el codigo del usuario borrado debemos obtener una excepción indicando que ya no existe
        Assertions.assertThrows(Exception.class, () -> productoServicio.obtenerProducto(codigoBorrado));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarProductosUsuarioTest() throws Exception {
        List<ProductoGetDTO> lista = productoServicio.listarProductosUsuario(2);

        Assertions.assertEquals(2,lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarProductosCategoriaTest() throws Exception {
        List<ProductoGetDTO> lista = productoServicio.listarProductosCategoria(Categoria.MODA);
        //lista.forEach(System.out::println);

        Assertions.assertEquals(1,lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarProductosPorEstadoTest()throws Exception {
        List<ProductoGetDTO> lista = productoServicio.listarProductosPorEstado(Estado.DENEGADO);
        Assertions.assertEquals(2,lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarProductosEstadoModeradorTest()throws Exception {
        List<ProductoGetDTO> lista = productoServicio.listarProductosEstadoModerador(2, Estado.AUTORIZADO);
        Assertions.assertEquals(2,lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarProductosFavoritosTest() throws Exception {
        List<ProductoGetDTO> lista = productoServicio.listarProductosFavoritos(4);

        Assertions.assertEquals(2,lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarProductosNombreTest() throws Exception {
        List<ProductoGetDTO> lista = productoServicio.listarProductosNombre("Blusa");

        Assertions.assertEquals(1,lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarProductoPorPrecio() throws Exception{
        List<ProductoGetDTO> lista = productoServicio.listarProductosPrecio(500,30000);

        Assertions.assertEquals(1,lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearFavoritoTest() throws Exception{
        int codigoUsuario = 1 ;
        int codigoProducto = 1 ;

        productoServicio.crearFavorito(codigoUsuario, codigoProducto);

        List<ProductoGetDTO> lista = productoServicio.listarProductosFavoritos(1);

        //En la base de datos el usuario 1 tiene 2 favoritos, con el que se acaba de agregar quedan 3
        Assertions.assertEquals(3,lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarFavoritoTest() throws Exception{
        productoServicio.eliminarFavorito(1,4);

        List<ProductoGetDTO> lista = productoServicio.listarProductosFavoritos(1);

        //En la base de datos el usuario 1 tiene 2 favoritos, con el que se acaba de borrar queda 1
        Assertions.assertEquals(1,lista.size());
    }


}
