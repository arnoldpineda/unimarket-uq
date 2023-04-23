package co.edu.uniquindio.unimarket.controladores;

import co.edu.uniquindio.unimarket.dto.ComentarioDTO;
import co.edu.uniquindio.unimarket.dto.MensajeDTO;
import co.edu.uniquindio.unimarket.dto.QuejaGetDTO;
import co.edu.uniquindio.unimarket.servicios.interfaces.ComentarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comentario")
@AllArgsConstructor
public class ComentarioControlador {

    private final ComentarioServicio comentarioServicio;

    @PostMapping("/crear_comentario")
    public ResponseEntity<MensajeDTO> crearComentario(@RequestBody ComentarioDTO comentarioDTO) throws Exception{
        comentarioServicio.crearComentario(comentarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED, false, "Comentario publicado"));
    }

    @GetMapping("/listar_comentario/{codigoProducto}")
    public ResponseEntity<MensajeDTO> listarComentarios(@PathVariable int codigoProducto) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, comentarioServicio.listarComentarios(codigoProducto)));
    }

}
