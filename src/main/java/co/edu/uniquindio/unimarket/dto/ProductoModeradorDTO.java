package co.edu.uniquindio.unimarket.dto;

import co.edu.uniquindio.unimarket.entidades.Estado;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter
@Setter
public class ProductoModeradorDTO {

    @NotNull
    @NotBlank
    @Length(max = 50, message = "La contraseña debe tener máximo 50 caracteres")
    private String motivo;

    @Positive
    private int codigoProducto;

    @Positive
    private int codigoModerador;

    @Enumerated (EnumType.STRING)
    private Estado estado;
}
