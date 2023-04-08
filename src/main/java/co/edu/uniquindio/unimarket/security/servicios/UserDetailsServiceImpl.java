package co.edu.uniquindio.unimarket.security.servicios;

import co.edu.uniquindio.unimarket.entidades.Moderador;
import co.edu.uniquindio.unimarket.entidades.Usuario;
import co.edu.uniquindio.unimarket.repositorios.ModeradorRepo;
import co.edu.uniquindio.unimarket.repositorios.UsuarioRepo;
import co.edu.uniquindio.unimarket.security.modelo.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuarioRepo clienteRepo;
    private final ModeradorRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> cliente = Optional.ofNullable(clienteRepo.buscarUsuario(email));
        if (cliente.isEmpty()) {
            Optional<Moderador> admin = Optional.ofNullable(adminRepo.buscarModerador(email));
            if (admin.isEmpty()) throw new UsernameNotFoundException("El usuario no existe");
            return UserDetailsImpl.build(admin.get());
        } else {
            return UserDetailsImpl.build(cliente.get());
        }
    }
}
