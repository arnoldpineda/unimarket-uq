package co.edu.uniquindio.unimarket.controladores;

import co.edu.uniquindio.unimarket.dto.CambiarPasswordDTO;
import co.edu.uniquindio.unimarket.dto.MensajeDTO;
import co.edu.uniquindio.unimarket.servicios.interfaces.RecuperarPasswordServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/password")
@AllArgsConstructor
public class RecuperarPasswordControlador {

    private final RecuperarPasswordServicio recuperarPasswordServicio;

    @GetMapping("/recuperar/{email}")
    public ResponseEntity<MensajeDTO> recuperarPassword(@PathVariable String email) throws Exception {
        recuperarPasswordServicio.recuperarPassword(email);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, "Correo enviado"));
    }

    @PostMapping("/cambiar")
    public ResponseEntity<MensajeDTO> cambiarPassword(@RequestBody CambiarPasswordDTO cambiarPasswordDTO) throws Exception {
        recuperarPasswordServicio.cambiarPassword(cambiarPasswordDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, "Contraseña Cambiada"));
    }
}
