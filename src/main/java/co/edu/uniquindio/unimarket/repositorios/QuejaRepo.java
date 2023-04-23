package co.edu.uniquindio.unimarket.repositorios;

import co.edu.uniquindio.unimarket.dto.QuejaGetDTO;
import co.edu.uniquindio.unimarket.entidades.Queja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuejaRepo extends JpaRepository<Queja, Integer>{

    @Query("select q from Queja q where q.usuario.codigo = :codigoUsuario")
    List<Queja> listaQuejasUsuario(int codigoUsuario);

    @Query("select q from Queja q where q.codigo = :radicado")
    QuejaGetDTO buscarradicado(int radicado);
}
