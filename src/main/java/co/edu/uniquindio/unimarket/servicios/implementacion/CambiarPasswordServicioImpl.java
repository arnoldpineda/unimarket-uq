package co.edu.uniquindio.unimarket.servicios.implementacion;

import co.edu.uniquindio.unimarket.dto.EmailDTO;
import co.edu.uniquindio.unimarket.repositorios.ModeradorRepo;
import co.edu.uniquindio.unimarket.repositorios.UsuarioRepo;
import co.edu.uniquindio.unimarket.servicios.interfaces.CambiarPasswordServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.EmailServicio;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CambiarPasswordServicioImpl implements CambiarPasswordServicio {

    private EmailServicio emailServicio;
    private UsuarioRepo clienteRepo;
    private ModeradorRepo moderadorRepo;

    @Override
    public void recuperarPassword(String email) throws Exception {

        if (email != clienteRepo.buscarUsuario(email).getEmail()) {
            if (email != moderadorRepo.buscarModerador(email).getEmail()){
                throw new UsernameNotFoundException("El correo " + email + " no esta registrado");
            }
        }else {
            emailServicio.enviarEmail(new EmailDTO(
                    "Solicitud cambiar password",
                    "Para recuperar su password debe dar clik en el enlace http.unimarket.cambiarpassword",
                    email
            ));
        }
    }


    //public void cambiarPassword(){}

}
