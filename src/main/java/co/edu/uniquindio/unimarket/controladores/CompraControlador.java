package co.edu.uniquindio.unimarket.controladores;

import co.edu.uniquindio.unimarket.dto.CompraDTO;
import co.edu.uniquindio.unimarket.dto.DetalleCompraDTO;
import co.edu.uniquindio.unimarket.dto.MensajeDTO;
import co.edu.uniquindio.unimarket.servicios.interfaces.CompraServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/compra")
@AllArgsConstructor
public class CompraControlador {

    private final CompraServicio compraServicio;

    @PostMapping("/crear_compra")
    public ResponseEntity<MensajeDTO> crearCompra(@RequestBody CompraDTO compraDTO) throws Exception{
        compraServicio.crearCompra(compraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED, false, "Compra realizada correctamente" ));
    }


    @PostMapping("/agregar_compra")
    public ResponseEntity<MensajeDTO> crearDetalleCompra(@RequestBody DetalleCompraDTO detalleCompraDTO) throws Exception{
        compraServicio.crearDetalleCompra(detalleCompraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED, false, "Agregado al carrito" ));
    }

    @GetMapping("/listar/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> listarCompras(@PathVariable int codigoUsuario) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, compraServicio.listarCompras(codigoUsuario)));
    }


    @GetMapping("/obtener_compra/{codigoCompra}")
    public ResponseEntity<MensajeDTO> obtenerDTO(@PathVariable int codigoCompra) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, compraServicio.obtenerDTO(codigoCompra)));
    }


}
