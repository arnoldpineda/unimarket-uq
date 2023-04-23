package co.edu.uniquindio.unimarket.repositorios;

import co.edu.uniquindio.unimarket.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoRepo extends JpaRepository<Producto, Integer> {
}
