package co.edu.uniquindio.unimarket.repositorios;

import co.edu.uniquindio.unimarket.entidades.Categoria;
import co.edu.uniquindio.unimarket.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {


    @Query("select p from Producto p where p.vendedor.codigo = :codigoUsuario")
    List<Producto> listarProductosUsuario(int codigoUsuario);

    //Es boleano true
    @Query("select p from Producto p where p.nombre like concat( '%', :nombre, '%' ) and current_date <= p.fechaLimite and p.activo = true")
    List<Producto> listarProductosNombre(String nombre);


    //@Query("select p from Producto p where :categoria member of p.categoria and p.fechaLimite <= current_date and p.activo =true") //no me funciona con la fecha
    @Query("select p from Producto p where :categoria member of p.categoria and current_date <= p.fechaLimite and p.activo =true")
    List<Producto> listarProductoCategoria(Categoria categoria);


    @Query("select f from Usuario u join u.favoritos f where u.codigo = :codigoUsuario and f.activo =true ") // como uno el estado, para mi si esta activo esta aprobado
    List<Producto> listarFavoritos(int codigoUsuario);


    @Query("select p from Producto p where p.precio between :precioMin and :precioMax and current_date <= p.fechaLimite and p.activo =true")
    List<Producto> listarPorPrecio(float precioMin, float precioMax);
}
