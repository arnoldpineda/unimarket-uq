package co.edu.uniquindio.unimarket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class QuejaGetDTO {

    private int codigo;
    private String Descripcion;
    private int codigoUsuario;
    private LocalDateTime fechaPublicacion;
}
