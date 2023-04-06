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
public class UsuarioDTO {

    @NotNull
    @NotBlank
    @Length(max = 50, message = "El nombre debe tener máximo 50 caracteres")
    private String nombre;

    @NotNull
    @NotBlank
    @Length(max = 50, message = "El correo debe tener máximo 50 caracteres")
    private String email;

    @NotNull
    @NotBlank
    @Length(max = 10, message = "La contraseña debe tener máximo 10 caracteres")
    private String password;

    @NotNull
    @NotBlank
    @Length(max = 100, message = "La dirección debe tener máximo 100 caracteres")
    private String direccion;

    @NotNull
    @NotBlank
    @Length(max = 10, message = "El teléfono debe tener máximo 10 caracteres")
    private String telefono;
}
