package co.edu.uniquindio.unimarket.repositorios;

import co.edu.uniquindio.unimarket.entidades.Moderador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeradorRepo extends JpaRepository<Moderador, Integer> {

    @Query
    ("select m from Moderador m where m.email = :correo")
    Moderador buscarModerador(String correo);
}
