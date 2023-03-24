package co.edu.uniquindio.unimarket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ComentarioGetDTO {

    private int codigo;
    private LocalDateTime fecha;
    private String mensaje;
    private int codigoUsuario;
    private int codigoProducto;
}
