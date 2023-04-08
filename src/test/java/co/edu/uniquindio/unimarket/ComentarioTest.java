package co.edu.uniquindio.unimarket;

import co.edu.uniquindio.unimarket.dto.ComentarioDTO;
import co.edu.uniquindio.unimarket.dto.ComentarioGetDTO;
import co.edu.uniquindio.unimarket.servicios.interfaces.ComentarioServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
@Transactional
public class ComentarioTest {

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void crearComentarioTest()throws Exception{

        //Se crea el comentario
        ComentarioDTO comentarioDTO = new ComentarioDTO(
                "Quiero comprar mas unidades de las que estan disponibles",
                1,
                4
        );

        //Se llama el servicio para crear el comentario
        int codigoComenatario = comentarioServicio.crearComentario(comentarioDTO);

        //Se espera que el servicio retorne el código del nuevo comentario
        Assertions.assertNotEquals(0,codigoComenatario);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarComentarioTest() throws Exception{
        List<ComentarioGetDTO> lista = comentarioServicio.listarComentarios(1);
        Assertions.assertEquals(2,lista.size());
    }
}
