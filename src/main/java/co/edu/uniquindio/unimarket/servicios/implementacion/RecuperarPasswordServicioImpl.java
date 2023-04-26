package co.edu.uniquindio.unimarket.servicios.implementacion;

import co.edu.uniquindio.unimarket.dto.CambiarPasswordDTO;
import co.edu.uniquindio.unimarket.dto.EmailDTO;
import co.edu.uniquindio.unimarket.entidades.Moderador;
import co.edu.uniquindio.unimarket.entidades.Usuario;
import co.edu.uniquindio.unimarket.repositorios.ModeradorRepo;
import co.edu.uniquindio.unimarket.repositorios.UsuarioRepo;
import co.edu.uniquindio.unimarket.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.RecuperarPasswordServicio;
import co.edu.uniquindio.unimarket.servicios.utilidades.Utilidades;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecuperarPasswordServicioImpl implements RecuperarPasswordServicio {

    @Autowired
    private final PasswordEncoder passwordEncoder;
    private EmailServicio emailServicio;
    private UsuarioRepo clienteRepo;
    private ModeradorRepo moderadorRepo;

    @Override
    public void recuperarPassword(String email) throws Exception {
        if (clienteRepo.buscarUsuario(email) == null && moderadorRepo.buscarModerador(email) == null) {
            throw new UsernameNotFoundException("El correo " + email + " no esta registrado");
        } else {
            String secret = "Army!03";
            String url = "https://www.unimarket.com/cambiar_password/";
            String encriptado = Utilidades.encriptar(email, secret);

            String asunto = "Solicitud cambiar password";
            String cuerpo = "Para recuperar su password debe dar clic en el siguiente enlace " + url + encriptado;

            emailServicio.enviarEmail(new EmailDTO(asunto, cuerpo, email));
        }
    }

    @Override
    public void cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws Exception {
        String secret = "Army!03";
        String email = Utilidades.desencriptar(cambiarPasswordDTO.getEmailEncriptado(), secret).trim();
        Usuario usuario = clienteRepo.buscarUsuario(email);
        Moderador moderador = moderadorRepo.buscarModerador(email);

        if (usuario != null) {
            usuario.setPassword(passwordEncoder.encode(cambiarPasswordDTO.getPassword()));
            clienteRepo.save(usuario);
        } else if (moderador != null) {
            moderador.setPassword(passwordEncoder.encode(cambiarPasswordDTO.getPassword()));
            moderadorRepo.save(moderador);
        } else {
            throw new UsernameNotFoundException("El correo " + email + " no esta registrado");
        }
    }
}
