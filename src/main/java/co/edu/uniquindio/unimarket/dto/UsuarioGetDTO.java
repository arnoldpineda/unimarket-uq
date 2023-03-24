package co.edu.uniquindio.unimarket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UsuarioGetDTO {
    private int codigo;
    private String nombre;
    private String email;
    private String direccion;
    private String telefono;
}
