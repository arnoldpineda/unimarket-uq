package co.edu.uniquindio.unimarket.controladores;

import co.edu.uniquindio.unimarket.dto.MensajeDTO;
import co.edu.uniquindio.unimarket.dto.QuejaDTO;
import co.edu.uniquindio.unimarket.dto.QuejaGetDTO;
import co.edu.uniquindio.unimarket.servicios.interfaces.QuejaServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/queja")
@AllArgsConstructor
public class QuejaControlador {

    private final QuejaServicio quejaServicio;

    @PostMapping("/crear_queja")
    public ResponseEntity<MensajeDTO> crearQueja(@RequestBody QuejaDTO quejaDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED, false, "Queja creada con radicado "+ quejaServicio.crearQueja(quejaDTO)));
    }


    @GetMapping("/lista_quejas/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> listarQuejas(@PathVariable int codigoUsuario) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, quejaServicio.listarQuejas(codigoUsuario)));
    }

    @GetMapping ("/buscar_radicado/{radicado}")
    public ResponseEntity<MensajeDTO> buscarRadicado (@PathVariable int radicado) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, quejaServicio.buscarRadicado(radicado)));

    }
}
