package co.edu.uniquindio.unimarket.controladores;

import co.edu.uniquindio.unimarket.dto.FavoritoDTO;
import co.edu.uniquindio.unimarket.dto.MensajeDTO;
import co.edu.uniquindio.unimarket.dto.UsuarioDTO;
import co.edu.uniquindio.unimarket.dto.UsuarioGetDTO;
import co.edu.uniquindio.unimarket.entidades.Usuario;
import co.edu.uniquindio.unimarket.servicios.interfaces.ProductoServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/usuario")
@AllArgsConstructor
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;
    private final ProductoServicio productoServicio;


    @PutMapping("/actualizar/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> actualizarUsuario(@PathVariable int codigoUsuario, @RequestBody UsuarioDTO usuarioDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, usuarioServicio.actualizarUsuario(codigoUsuario, usuarioDTO)));
    }


    @DeleteMapping("/eliminar/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> eliminiarUsuario(@PathVariable int codigoUsuario) throws Exception{
        usuarioServicio.eliminiarUsuario(codigoUsuario);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, "Usuario eliminado correctamente"));
    }


    @GetMapping("/obtener/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> obtenerUsuario(@PathVariable int codigoUsuario) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, usuarioServicio.obtenerUsuario(codigoUsuario)));
    }



    @PutMapping("/agregar_favorito/{codigoUsuario}/{codigoProducto}")
    public ResponseEntity<MensajeDTO> obtenerUsuario(@PathVariable int codigoUsuario, @PathVariable int codigoProducto) throws Exception {
        productoServicio.agregarFavorito(codigoUsuario, codigoProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED, false, "Producto agregado a favoritos"));
    }


    @GetMapping("/listar_favorito/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> listarProductosFavoritos(@PathVariable int codigoUsuario) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, productoServicio.listarProductosFavoritos(codigoUsuario)));
    }


    //desde la clase producto servicio
    @DeleteMapping("/eliminar_favorito/{codigoUsuario}/{codigoProducto}")
    public ResponseEntity<MensajeDTO> eliminarFavorito(@PathVariable int codigoUsuario, @PathVariable int codigoProducto) throws Exception{
        productoServicio.eliminarFavorito(codigoUsuario, codigoProducto);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, "Producto eliminado de favoritos"));
    }



     


        //void logout(int codigoUsuario);

}
