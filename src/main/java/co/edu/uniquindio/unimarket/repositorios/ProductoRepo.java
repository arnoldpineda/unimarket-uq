package co.edu.uniquindio.unimarket.repositorios;

import co.edu.uniquindio.unimarket.entidades.Categoria;
import co.edu.uniquindio.unimarket.entidades.Estado;
import co.edu.uniquindio.unimarket.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {


    @Query("select p from Producto p where p.vendedor.codigo = :codigoUsuario")
    List<Producto> listarProductosUsuario(int codigoUsuario);

    //Estado activo es boleano true
    @Query("select p from Producto p where p.nombre like concat( '%', :nombre, '%' ) and current_date <= p.fechaLimite and p.activo =  co.edu.uniquindio.unimarket.entidades.Estado.AUTORIZADO")
    List<Producto> listarProductosNombre(String nombre);


    //@Query("select p from Producto p where :categoria member of p.categoria and p.fechaLimite <= current_date and p.activo =true") //no me funciona con la fecha
    @Query("select p from Producto p where :categoria member of p.categoria and current_date <= p.fechaLimite and p.activo =  co.edu.uniquindio.unimarket.entidades.Estado.AUTORIZADO")
    List<Producto> listarProductoCategoria(Categoria categoria);


    @Query("select f from Usuario u join u.favoritos f where u.codigo = :codigoUsuario and f.activo = co.edu.uniquindio.unimarket.entidades.Estado.AUTORIZADO")
    List<Producto> listarFavoritos(int codigoUsuario);


    @Query("select p from Producto p where p.precio between :precioMin and :precioMax and current_date <= p.fechaLimite and p.activo = co.edu.uniquindio.unimarket.entidades.Estado.AUTORIZADO")
    List<Producto> listarPorPrecio(float precioMin, float precioMax);


    //Lista si los producto estan Autorizados, denegados sin revisar, los autorizados son los que se deben mostrar en la pag inicio
    @Query ("select p from Producto p where p.activo = :estado and current_date <= p.fechaLimite")
    List<Producto> listarProductosEstado(Estado estado);


    //Lista los productos de acuerdo al estado que puso el moderador aprobado, denegado
    @Query("select p.producto from ProductoModerador p where p.moderador.codigo = :codigoModerador and p.estado = :estado and current_date <= p.producto.fechaLimite")
    List<Producto> listarProductosEstadoModerador(int codigoModerador, Estado estado);

    @Query ("select p from Producto p where p.activo = co.edu.uniquindio.unimarket.entidades.Estado.AUTORIZADO and current_date <= p.fechaLimite")
    List<Producto> listarProductosDisponibles();


}
