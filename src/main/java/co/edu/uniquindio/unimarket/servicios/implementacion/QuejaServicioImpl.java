package co.edu.uniquindio.unimarket.servicios.implementacion;

import co.edu.uniquindio.unimarket.dto.EmailDTO;
import co.edu.uniquindio.unimarket.dto.QuejaDTO;
import co.edu.uniquindio.unimarket.dto.QuejaGetDTO;
import co.edu.uniquindio.unimarket.entidades.Queja;
import co.edu.uniquindio.unimarket.repositorios.QuejaRepo;
import co.edu.uniquindio.unimarket.servicios.excepcion.ListaVaciaException;
import co.edu.uniquindio.unimarket.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.QuejaServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class QuejaServicioImpl implements QuejaServicio {

    private final QuejaRepo quejaRepo;
    private final UsuarioServicio usuarioServicio;
    private EmailServicio emailServicio;


    @Override
    public int crearQueja(QuejaDTO quejaDTO) throws Exception {
        Queja queja = new Queja();
        queja.setFechaPublicacion(LocalDateTime.now());
        queja.setDescripcion(quejaDTO.getDescripcion());
        queja.setUsuario(usuarioServicio.obtener(quejaDTO.getCodigoUsuario()));

        int radicado = quejaRepo.save(queja).getCodigo();
        //Se envia un correo al usuario con el codigo y mensaje de la queja
        emailServicio.enviarEmail(new EmailDTO(
                "Notificacion registro de queja con radicado numero "+ radicado,
                "Confirmamos el registro de su solicitud: " + quejaDTO.getDescripcion() ,
                usuarioServicio.obtener(quejaDTO.getCodigoUsuario()).getEmail()
        ));

        return radicado;
    }

    @Override
    public List<QuejaGetDTO> listarQuejas(int codigoUsuario) throws Exception {
        List<Queja> lista = quejaRepo.listaQuejasUsuario(codigoUsuario);

        if(lista.isEmpty()){
            throw new ListaVaciaException("El usuario no tiene quejas radicadas");
        }

        List<QuejaGetDTO> respuesta = new ArrayList<>();

        for (Queja q : lista){
            respuesta.add(convertir(q));
        }
        return respuesta;
    }

    private QuejaGetDTO convertir(Queja queja) {
        QuejaGetDTO quejaGetDTO = new QuejaGetDTO(
                queja.getCodigo(),
                queja.getDescripcion(),
                queja.getUsuario().getCodigo(),
                queja.getFechaPublicacion()
        );
    return quejaGetDTO;
    }
}













