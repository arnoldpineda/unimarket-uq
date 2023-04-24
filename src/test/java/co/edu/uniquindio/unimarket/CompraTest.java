package co.edu.uniquindio.unimarket;

import co.edu.uniquindio.unimarket.dto.CompraDTO;
import co.edu.uniquindio.unimarket.dto.CompraGetDTO;
import co.edu.uniquindio.unimarket.dto.DetalleCompraDTO;
import co.edu.uniquindio.unimarket.entidades.Compra;
import co.edu.uniquindio.unimarket.entidades.MetodoPago;
import co.edu.uniquindio.unimarket.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.unimarket.servicios.interfaces.CompraServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.ProductoServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class CompraTest {

    @Autowired
    private CompraServicio compraServicio;
    @Autowired
    private ProductoServicio productoServicio;

    //Crear compra
    @Test
    @Sql("classpath:dataset.sql")
    public void crearCompraTest()throws Exception{

        DetalleCompraDTO detalleCompraDTO1 = new DetalleCompraDTO(1, 2, 4545);
        DetalleCompraDTO detalleCompraDTO2 = new DetalleCompraDTO(2, 2, 7838);

        ArrayList<DetalleCompraDTO> listaDc = new ArrayList<>();
        listaDc.add(detalleCompraDTO1);
        listaDc.add(detalleCompraDTO2);
        // Assertions.assertEquals(2, listaDc.size());

        //Se crea la compra
        CompraDTO compraDTO = new CompraDTO(
                1,
                MetodoPago.VISA,
                listaDc
        );

        //Se llama el servicio para crear la compra
        int codigoCompra = compraServicio.crearCompra(compraDTO);
        //System.out.print("can actualizada de unidades: " + productoServicio.obtenerProducto(1).getUnidades());
        Assertions.assertNotEquals(0,codigoCompra);
    }


    //listar compras
    @Test
    @Sql("classpath:dataset.sql")
    public void listarCompraTest()throws Exception{
        List<CompraGetDTO> lista = compraServicio.listarCompras(3);
        Assertions.assertEquals(2, lista.size());
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCompraTest()throws Exception{
        Compra compra = (compraServicio.obtenerCompra(1));
        //System.out.print(compra);
        Assertions.assertNotNull(compra);
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerDtoTest()throws Exception{
        CompraGetDTO compraGetDTO =compraServicio.obtenerDTO(1);
        Assertions.assertNotNull(compraGetDTO);
    }


}



