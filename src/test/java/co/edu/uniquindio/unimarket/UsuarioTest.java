package co.edu.uniquindio.unimarket;

import co.edu.uniquindio.unimarket.dto.*;
import co.edu.uniquindio.unimarket.entidades.Usuario;
import co.edu.uniquindio.unimarket.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.ProductoServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.UsuarioServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
@Transactional
public class UsuarioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private EmailServicio emailServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void crearUsuarioTest() throws Exception{

        //Se crea el usuario con el servicio de crearUsuario
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "Pepito 1",
                "pepe1@email.com",
                "1234",
                "Calle 123",
                "343");

        int codigo = usuarioServicio.crearUsuario(usuarioDTO);

        System.out.print(usuarioServicio.obtener(codigo).getPassword());
        //Se espera que si se registra correctamente entonces el servicio no debe retornar 0
        Assertions.assertNotEquals(0, codigo);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarUsuarioTest() throws Exception{

        //UsuarioGetDTO usuarioGetDTO = usuarioServicio.obtenerUsuario(1);
        //System.out.print(usuarioGetDTO.getNombre());

        //El servicio de actualizar nos retorna el usuario
        UsuarioGetDTO usuarioActualizado = usuarioServicio.actualizarUsuario(1, new UsuarioDTO("Juan Perez", "pepitoperez@email.com", "1234", "Calle 123", "1111"));

        //Se comprueba que ahora el teléfono del usuario no es el que se usó cuando se creó inicialmente
        Assertions.assertNotEquals("2782", usuarioActualizado.getTelefono());

        //System.out.print(usuarioActualizado.getNombre());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarUsuarioTest() throws Exception{

        int codigoBorrado = usuarioServicio.eliminiarUsuario(1);

        //Si intentamos buscar un usuario con el codigo del usuario borrado debemos obtener una excepción indicando que ya no existe
        Assertions.assertThrows(Exception.class, () -> usuarioServicio.obtenerUsuario(codigoBorrado));

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerUsuarioTest()throws Exception{

        //Se llama el servicio para obtener el usuario completo dado su código
        UsuarioGetDTO usuarioGetDTO = usuarioServicio.obtenerUsuario(1);

        //Comprobamos que la dirección que está en la base de datos coincide con la que esperamos
        Assertions.assertEquals("Cra 53", usuarioGetDTO.getDireccion());
        //System.out.print(usuarioGetDTO.getDireccion());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener()throws Exception{
        Usuario usuario = (usuarioServicio.obtener(1));
        //Se espera que si existe entonces el servicio no debe retornar 0

        Assertions.assertNotEquals(0, usuario.getCodigo());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void validarExiste()throws Exception{

        //Se llama el servicio para obtener el usuario completo dado su código
        UsuarioGetDTO usuarioGetDTO = usuarioServicio.obtenerUsuario(1);
        Assertions.assertNotNull(usuarioGetDTO);
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void enviarEmailTest() throws Exception{
        emailServicio.enviarEmail(new EmailDTO("Prueba", "Esta es una prueba", "jmllantenm@uqvirtual.edu.co"));
        //Se verifica en el correo, asunto y mensaje ok
    }

    /* Eliminar
    @Test
    @Sql("classpath:dataset.sql")
    public void crearFavoritoTest() throws Exception{

        FavoritoDTO favoritoDTO = new FavoritoDTO(1,1);

        usuarioServicio.agregarFavorito(favoritoDTO);

        List<ProductoGetDTO> lista = productoServicio.listarProductosFavoritos(1);

        //En la base de datos el usuario 1 tiene 2 favoritos, con el que se acaba de agregar quedan 3
        Assertions.assertEquals(3,lista.size());
    }

     */

}
