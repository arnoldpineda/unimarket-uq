package co.edu.uniquindio.unimarket.repositorios;

import co.edu.uniquindio.unimarket.entidades.Comentario;
import co.edu.uniquindio.unimarket.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, Integer>{

    @Query("select c from Comentario c where c.producto.codigo = :codigoProducto")
    List<Comentario> listaComentarios(int codigoProducto);


}
