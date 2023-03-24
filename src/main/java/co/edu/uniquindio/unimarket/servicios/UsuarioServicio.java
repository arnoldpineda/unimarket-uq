package co.edu.uniquindio.unimarket.servicios;

import co.edu.uniquindio.unimarket.dto.UsuarioDTO;

public interface UsuarioServicio {
    int crearUsuario(UsuarioDTO usuarioDTO);

    int actualizarUsuario(int codigoUsuario, UsuarioDTO usuarioDTO);

    int eliminiarUsuario(int codigoUsuario);

    UsuarioDTO obtenerUsuario(int codigoUsuario);
}
