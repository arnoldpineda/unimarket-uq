package co.edu.uniquindio.unimarket.servicios.implementacion;

import co.edu.uniquindio.unimarket.dto.UsuarioDTO;
import co.edu.uniquindio.unimarket.dto.UsuarioGetDTO;
import co.edu.uniquindio.unimarket.entidades.Usuario;
import co.edu.uniquindio.unimarket.repositorios.UsuarioRepo;
import co.edu.uniquindio.unimarket.servicios.excepcion.AttributeException;
import co.edu.uniquindio.unimarket.servicios.excepcion.ObjetoNoEncontradoException;
import co.edu.uniquindio.unimarket.servicios.interfaces.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public int crearUsuario(UsuarioDTO usuarioDTO) throws Exception {

        validarCorreoExiste(usuarioDTO.getEmail()); //valida si el correo ya esta en uso
        Usuario usuario = convertirDTO(usuarioDTO);

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        
        return usuarioRepo.save(usuario).getCodigo() ;
    }

    @Override
    public UsuarioGetDTO actualizarUsuario(int codigoUsuario, UsuarioDTO usuarioDTO) throws Exception {

        validarExiste(codigoUsuario); //Valida que el codigo exista

        //Se valida si el correo ya esta en uso por otro usuario si es diferente al correo actual
        if(!obtener(codigoUsuario).getEmail().equals(usuarioDTO.getEmail())){
            validarCorreoExiste(usuarioDTO.getEmail());
        }

        Usuario usuario = convertirDTO(usuarioDTO);
        usuario.setCodigo(codigoUsuario);
        return convertir(usuarioRepo.save(usuario));
    }

    @Override
    public int eliminiarUsuario(int codigoUsuario) throws Exception {
        validarExiste(codigoUsuario);
        usuarioRepo.deleteById(codigoUsuario);
        return codigoUsuario;
    }

    //obtiene el usuario del codigo enviado, lo convierte a DTO y se retorna un DTO con todos los datos
    @Override
    public UsuarioGetDTO obtenerUsuario(int codigoUsuario) throws Exception {
        return convertir( obtener(codigoUsuario) );
    }

    @Override
    public Usuario obtener(int codigoUsuario) throws Exception {
        Optional<Usuario> usuario = usuarioRepo.findById(codigoUsuario);

        if(usuario.isEmpty() ){
            throw new ObjetoNoEncontradoException("El código "+codigoUsuario+" no está asociado a ningún usuario");
        }

        return usuario.get();
    }

    private void validarExiste(int codigoUsuario) throws Exception{
        boolean existe = usuarioRepo.existsById(codigoUsuario);

        if( !existe ){
            throw new ObjetoNoEncontradoException("El código "+codigoUsuario+" no está asociado a ningún usuario");
        }
    }

    private void validarCorreoExiste(String email) throws Exception{
        Usuario buscado = usuarioRepo.buscarUsuario(email);

        if(buscado!=null){
            throw new AttributeException("El correo "+ email +" ya esta en uso" );
        }
    }

    private UsuarioGetDTO convertir(Usuario usuario){
        return new UsuarioGetDTO(
            usuario.getCodigo(),
            usuario.getNombre(),
            usuario.getEmail(),
            usuario.getDireccion(),
            usuario.getTelefono());
    }
    private Usuario convertirDTO(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
            usuario.setNombre( usuarioDTO.getNombre() );
            usuario.setEmail( usuarioDTO.getEmail() );
            usuario.setDireccion( usuarioDTO.getDireccion() );
            usuario.setTelefono( usuarioDTO.getTelefono() );
            usuario.setPassword( usuarioDTO.getPassword() );
        return usuario;
    }

}
