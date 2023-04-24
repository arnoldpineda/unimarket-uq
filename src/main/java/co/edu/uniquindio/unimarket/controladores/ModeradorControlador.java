package co.edu.uniquindio.unimarket.controladores;

import co.edu.uniquindio.unimarket.dto.MensajeDTO;
import co.edu.uniquindio.unimarket.dto.ProductoModeradorDTO;
import co.edu.uniquindio.unimarket.entidades.Estado;
import co.edu.uniquindio.unimarket.servicios.interfaces.ProductoModeradorServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.ProductoServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/moderador")
@AllArgsConstructor
@PreAuthorize("hasAuthority('MODERADOR')")
public class ModeradorControlador {

    private final ProductoModeradorServicio productoModeradorServicio;

    private final ProductoServicio productoServicio;


    @PostMapping("/registrar_estado")
    public ResponseEntity<MensajeDTO> registrarCambioEstado(@RequestBody ProductoModeradorDTO productoModeradorDTO) throws Exception{
        productoModeradorServicio.registrarCambioEstado(productoModeradorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body( new MensajeDTO(HttpStatus.CREATED, false, "Estado actualizado" ));
    }

    @GetMapping("/listar_estado/{estado}")
    public ResponseEntity<MensajeDTO> listarProductosPorEstado(@PathVariable Estado estado) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, productoServicio.listarProductosPorEstado(estado)));
    }

    @GetMapping("/listar_moderador/{codigoModerador}/{estado}")
    public ResponseEntity<MensajeDTO> listarProductosEstadoModerador(@PathVariable int codigoModerador, @PathVariable Estado estado) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, productoServicio.listarProductosEstadoModerador(codigoModerador, estado)));
    }
}
