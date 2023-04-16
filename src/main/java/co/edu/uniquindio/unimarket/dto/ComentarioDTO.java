package co.edu.uniquindio.unimarket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @Positive
    private int codigoUsuario;

    @Positive
    private int codigoProducto;
}
