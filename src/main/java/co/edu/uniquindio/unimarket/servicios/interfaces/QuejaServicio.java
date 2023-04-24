package co.edu.uniquindio.unimarket.servicios.interfaces;

import co.edu.uniquindio.unimarket.dto.QuejaDTO;
import co.edu.uniquindio.unimarket.dto.QuejaGetDTO;
import co.edu.uniquindio.unimarket.entidades.Queja;

import java.util.List;

public interface QuejaServicio {

    int crearQueja(QuejaDTO quejaDTO) throws Exception;

    List<QuejaGetDTO> listarQuejas(int codigoUsuario)throws Exception;

    QuejaGetDTO buscarRadicado(int radicado) throws Exception;

    Queja obtener(int codigoQueja) throws Exception;
}
