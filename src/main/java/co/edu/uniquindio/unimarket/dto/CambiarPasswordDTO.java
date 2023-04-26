package co.edu.uniquindio.unimarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CambiarPasswordDTO {

    private String emailEncriptado;
    private String password;
}
