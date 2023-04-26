package co.edu.uniquindio.unimarket.servicios.interfaces;

import co.edu.uniquindio.unimarket.dto.CambiarPasswordDTO;

public interface RecuperarPasswordServicio {

    void recuperarPassword(String email) throws Exception;

    void cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws Exception;
}
