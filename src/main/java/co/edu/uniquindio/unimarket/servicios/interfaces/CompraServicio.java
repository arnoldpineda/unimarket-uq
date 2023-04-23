package co.edu.uniquindio.unimarket.servicios.interfaces;

import co.edu.uniquindio.unimarket.dto.CompraDTO;
import co.edu.uniquindio.unimarket.dto.CompraGetDTO;
import co.edu.uniquindio.unimarket.dto.DetalleCompraDTO;
import co.edu.uniquindio.unimarket.entidades.Compra;
import co.edu.uniquindio.unimarket.entidades.DetalleCompra;

import java.util.List;

public interface CompraServicio {

    int crearCompra(CompraDTO compraDTO) throws Exception;

    DetalleCompra crearDetalleCompra(DetalleCompraDTO detalleCompraDTO) throws Exception;

    List<CompraGetDTO> listarCompras(int codigoUsuario) throws Exception;

    Compra obtenerCompra(int codigoCompra) throws Exception;

    CompraGetDTO obtenerDTO(int codigoCompra) throws Exception;
}
