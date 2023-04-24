package co.edu.uniquindio.unimarket.servicios.interfaces;

import co.edu.uniquindio.unimarket.dto.ProductoDTO;
import co.edu.uniquindio.unimarket.dto.ProductoGetDTO;
import co.edu.uniquindio.unimarket.entidades.Categoria;
import co.edu.uniquindio.unimarket.entidades.Estado;
import co.edu.uniquindio.unimarket.entidades.Producto;

import java.util.List;

public interface ProductoServicio {

    int crearProducto(ProductoDTO productoDTO) throws Exception;

    ProductoGetDTO actualizarProducto(int codigoProducto, ProductoDTO productoDTO) throws Exception;

    int actualizarUnidades(int codigoProducto, int unidades) throws Exception;

    int actualizarEstado(int codigoProducto, Estado estado) throws Exception;

    int eliminarProducto(int codigoProducto) throws Exception;

    ProductoGetDTO obtenerProducto(int codigoProducto) throws Exception;

    Producto obtener(int codigoProducto) throws Exception;

    List<ProductoGetDTO> listarProductosUsuario(int codigoUsuario) throws Exception;

    List<ProductoGetDTO> listarProductosCategoria(Categoria categoria) throws Exception;

    List<ProductoGetDTO> listarProductosPorEstado(Estado estado) throws Exception;

    List<ProductoGetDTO> listarProductosEstadoModerador(int codigoModerador, Estado estado) throws Exception;

    List<ProductoGetDTO> listarProductosFavoritos(int codigoUsuario) throws Exception;

    List<ProductoGetDTO> listarProductosDisponibles() throws Exception;

    List<ProductoGetDTO> listarProductosNombre(String nombre) throws Exception;

    List<ProductoGetDTO> listarProductosPrecio(float precioMinimo, float precioMaximo) throws Exception;


    void eliminarFavorito(int codigoUsuario, int codigoProducto) throws Exception;
}
