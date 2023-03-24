package co.edu.uniquindio.unimarket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ComentarioDTO {

    @NotNull
    @NotBlank
    private String mensaje;

    @NotNull
    @NotBlank
    private int codigoUsuario;

    @NotNull
    @NotBlank
    private int codigoProducto;
}
