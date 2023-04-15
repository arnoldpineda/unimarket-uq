package co.edu.uniquindio.unimarket.servicios.implementacion;

import co.edu.uniquindio.unimarket.dto.ComentarioDTO;
import co.edu.uniquindio.unimarket.dto.ComentarioGetDTO;
import co.edu.uniquindio.unimarket.dto.EmailDTO;
import co.edu.uniquindio.unimarket.entidades.Comentario;
import co.edu.uniquindio.unimarket.repositorios.ComentarioRepo;
import co.edu.uniquindio.unimarket.entidades.Producto;
import co.edu.uniquindio.unimarket.servicios.excepcion.ListaVaciaException;
import co.edu.uniquindio.unimarket.servicios.interfaces.ComentarioServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.ProductoServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentarioRepo comentarioRepo;
    private final UsuarioServicio usuarioServicio;
    private final ProductoServicio productoServicio;

    private EmailServicio emailServicio;

    @Override
    public int crearComentario(ComentarioDTO comentarioDTO) throws Exception {

        Producto producto = productoServicio.obtener(comentarioDTO.getCodigoProducto()); //se guarda el producto

        Comentario comentario = new Comentario();
        comentario.setMensaje(comentarioDTO.getMensaje());
        comentario.setFechaCreacion(LocalDateTime.now());
        comentario.setProducto(producto); //se asigna el producto guardado
        comentario.setUsuario(usuarioServicio.obtener(comentarioDTO.getCodigoUsuario()));

        //se debe enviar un email (al usuario que publicó el producto) con lo que escribió la persona.
        String emailVendedor = producto.getVendedor().getEmail();
        emailServicio.enviarEmail(new EmailDTO(
                "Nuevo comentario",
                comentarioDTO.getMensaje(),
                emailVendedor));

        return comentarioRepo.save(comentario).getCodigo();
    }

    @Override
    public List<ComentarioGetDTO> listarComentarios(int codigoProducto) throws Exception {
        List<Comentario> lista = comentarioRepo.listaComentarios(codigoProducto);

        if(lista.isEmpty()){
            throw new ListaVaciaException("Producto sin comentarios");
        }

        List<ComentarioGetDTO> respuesta = new ArrayList<>();

        for (Comentario c : lista){
            respuesta.add(convertir(c));
        }

        return respuesta;
    }

    private ComentarioGetDTO convertir(Comentario comentario) {
        ComentarioGetDTO comentarioGetDTO = new ComentarioGetDTO(
                comentario.getCodigo(),
                comentario.getFechaCreacion(),
                comentario.getMensaje(),
                comentario.getUsuario().getCodigo(),
                comentario.getProducto().getCodigo()
        );
        return comentarioGetDTO;
    }

}
