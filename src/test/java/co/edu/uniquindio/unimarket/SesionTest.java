package co.edu.uniquindio.unimarket;

import co.edu.uniquindio.unimarket.dto.SesionDTO;
import co.edu.uniquindio.unimarket.dto.TokenDTO;
import co.edu.uniquindio.unimarket.servicios.interfaces.SesionServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Transactional
public class SesionTest {

    @Autowired
    private SesionServicio sesionServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void loginTest(){
        SesionDTO sesionDTO = new SesionDTO(
                "pepitoperez@email.com",
                "1234"
        );
        //Se llama al servicio que genera el token
        TokenDTO tokenDTO = sesionServicio.login(sesionDTO);
        System.out.println(tokenDTO.getToken());
        Assertions.assertNotNull(tokenDTO);
    }


}
