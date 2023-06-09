package co.edu.uniquindio.unimarket.repositorios;

import co.edu.uniquindio.unimarket.entidades.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

    @Query
    ("select u from Usuario u where u.email = :correo")
    Usuario buscarUsuario(String correo);


}
