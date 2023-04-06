package co.edu.uniquindio.unimarket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter
@Setter
public class EmailDTO {

    @NotNull
    @NotBlank
    @Length(max = 20, message = "El aunto debe tener máximo 20 caracteres")
    private String asunto;

    @NotNull
    @NotBlank
    private String cuerpo;

    @NotNull
    @NotBlank
    @Length(max = 50, message = "El correo debe tener máximo 50 caracteres")
    private String destinatario;
}
