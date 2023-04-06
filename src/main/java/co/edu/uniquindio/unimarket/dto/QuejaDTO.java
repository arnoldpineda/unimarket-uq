package co.edu.uniquindio.unimarket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class QuejaDTO {

    @NotNull
    @NotBlank
    private String Descripcion;

    @NotNull
    @NotBlank
    private int codigoUsuario;
}


