package co.edu.uniquindio.unimarket.controladores;

import co.edu.uniquindio.unimarket.dto.MensajeDTO;
import co.edu.uniquindio.unimarket.dto.SesionDTO;
import co.edu.uniquindio.unimarket.dto.TokenDTO;
import co.edu.uniquindio.unimarket.dto.UsuarioDTO;
import co.edu.uniquindio.unimarket.servicios.interfaces.SesionServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.UsuarioServicio;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final UsuarioServicio usuarioServicio;
    private final SesionServicio sesionServicio;

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO> login(@Valid @RequestBody SesionDTO loginUser){
        TokenDTO jwtTokenDto = sesionServicio.login(loginUser);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, jwtTokenDto) );
    }

    @PostMapping("/crear_cliente")
    public ResponseEntity<MensajeDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) throws Exception{
        usuarioServicio.crearUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED, false, "Cliente creado correctamente")) ;
    }



}
