package co.edu.uniquindio.unimarket.servicios.interfaces;

import co.edu.uniquindio.unimarket.dto.ProductoModeradorDTO;

public interface ProductoModeradorServicio {

    void registrarCambioEstado(ProductoModeradorDTO productoModeradorDTO) throws Exception;
}
