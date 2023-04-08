package co.edu.uniquindio.unimarket;

import co.edu.uniquindio.unimarket.dto.QuejaDTO;
import co.edu.uniquindio.unimarket.dto.QuejaGetDTO;
import co.edu.uniquindio.unimarket.servicios.interfaces.QuejaServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
@Transactional
public class QuejaTest {

    @Autowired
    private QuejaServicio quejaServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void crearQuejaTest() throws Exception{

        //Se crea el DTO de la queja
        QuejaDTO quejaDTO = new QuejaDTO(
               "No me llegan los correos cuando vendo mis productos",
               3
        );
        //Se llama el servicio para crear la queja
        int codigoQueja = quejaServicio.crearQueja(quejaDTO);

        //Se espera que el servicio retorne el codigo de la queja creada
        Assertions.assertNotEquals(0,codigoQueja);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarQuejasUsuarioTest() throws Exception{
        List<QuejaGetDTO> lista = quejaServicio.listarQuejas(2);

        //Se espera que el servicio devuelva la cantidad de quejas del usuario
        Assertions.assertEquals(1,lista.size());
    }


}
