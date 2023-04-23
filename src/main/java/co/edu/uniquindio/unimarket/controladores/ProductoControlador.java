package co.edu.uniquindio.unimarket.controladores;

import co.edu.uniquindio.unimarket.dto.MensajeDTO;
import co.edu.uniquindio.unimarket.dto.ProductoDTO;
import co.edu.uniquindio.unimarket.dto.ProductoGetDTO;
import co.edu.uniquindio.unimarket.entidades.Categoria;
import co.edu.uniquindio.unimarket.servicios.interfaces.ProductoServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/producto")
public class ProductoControlador {

    private final ProductoServicio productoServicio;


    @PostMapping("/crear_producto")
    public ResponseEntity<MensajeDTO> crearProducto(@RequestBody ProductoDTO productoDTO) throws Exception{
        productoServicio.crearProducto(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED, false, "Producto creado correctamente" ));
    }

    @PutMapping("/actualizar_producto/{codigoProducto}")
    public ResponseEntity<MensajeDTO> actualizarProducto(@PathVariable int codigoProducto, @RequestBody ProductoDTO productoDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, productoServicio.actualizarProducto(codigoProducto, productoDTO)));
    }

    @DeleteMapping("/eliminar_producto/{codigoProducto}")
    public ResponseEntity<MensajeDTO> eliminarProducto(@PathVariable int codigoProducto) throws Exception{
        productoServicio.eliminarProducto(codigoProducto);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, "Producto eliminado correctamente"));
    }

    @GetMapping("/listar_mis_productos/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> listarProductosUsuario(@PathVariable int codigoUsuario) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, productoServicio.listarProductosUsuario(codigoUsuario)));
    }



    @GetMapping("/listar_disponibles")
    public ResponseEntity<MensajeDTO> listarProductosDisponibles() throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, productoServicio.listarProductosDisponibles()));
    }

    @GetMapping("/obtener/{codigoProducto}")
    public ResponseEntity<MensajeDTO> obtenerProducto(@PathVariable int codigoProducto) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, productoServicio.obtenerProducto(codigoProducto)));
    }

    @GetMapping("/listar_categoria/{categoria}")
    public ResponseEntity<MensajeDTO> listarProductosCategoria(@PathVariable Categoria categoria) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, productoServicio.listarProductosCategoria(categoria)));
    }

    @GetMapping("/listar_nombre/{nombre}")
    public ResponseEntity<MensajeDTO> listarProductosNombre(@PathVariable String nombre) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, productoServicio.listarProductosNombre(nombre)));
    }

    @GetMapping("/listar_precio/{precioMinimo}/{precioMaximo}")
    public ResponseEntity<MensajeDTO> listarProductosPrecio(@PathVariable float precioMinimo, @PathVariable float precioMaximo) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, productoServicio.listarProductosPrecio(precioMinimo, precioMaximo)));
    }


}
