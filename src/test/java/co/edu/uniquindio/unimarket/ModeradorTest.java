package co.edu.uniquindio.unimarket;

import co.edu.uniquindio.unimarket.dto.ProductoModeradorDTO;
import co.edu.uniquindio.unimarket.entidades.Estado;
import co.edu.uniquindio.unimarket.entidades.Moderador;
import co.edu.uniquindio.unimarket.entidades.Producto;
import co.edu.uniquindio.unimarket.servicios.interfaces.ModeradorServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.ProductoModeradorServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.ProductoServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
@Transactional
public class ModeradorTest {

    @Autowired
    private ModeradorServicio moderadorServicio;

    @Autowired
    private ProductoModeradorServicio productoModeradorServicio;
    @Autowired
    private ProductoServicio productoServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerTest() throws Exception{
        Moderador moderador = (moderadorServicio.obtener(2));
        Assertions.assertNotNull(moderador);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarCambioEstadoTest() throws Exception{
        ProductoModeradorDTO productoModeradorDTO = new ProductoModeradorDTO(
                "Todo es correcto",
                3,
                1,
                Estado.AUTORIZADO
        );

        //Se llama el servicio para crear el registro
        productoModeradorServicio.registrarCambioEstado(productoModeradorDTO);

        Producto producto = productoServicio.obtener(3);
        Assertions.assertEquals(Estado.AUTORIZADO, producto.getActivo()); //Compara que el estado enviado sea igual al estado actual
    }
}
