package co.edu.uniquindio.unimarket.servicios;

import co.edu.uniquindio.unimarket.dto.ProductoDTO;
import co.edu.uniquindio.unimarket.dto.ProductoGetDTO;
import co.edu.uniquindio.unimarket.entidades.Categoria;
import co.edu.uniquindio.unimarket.entidades.Estado;

import java.util.List;

public interface ProductoServicio {

    int crearProducto(ProductoDTO productoDTO);

    int actualizarProducto(int codigoProducto, ProductoDTO productoDTO);

    int actualizarUnidades(int codigoProducto, int unidades);

    int actualizarEstado(int codigoProducto, Estado estado);

    int eliminarProducto(int codigoProducto);

    ProductoGetDTO obtenerProducto(int codigoProducto);

    List<ProductoGetDTO> listarProductosUsuario(int codigoUsuario);

    List<ProductoGetDTO> listarProductosCategoria(Categoria categoria);

    List<ProductoGetDTO> listarProductosPorEstado(Estado estado);

    List<ProductoGetDTO> listarProductosFavoritos(int codigoUsuario);

    List<ProductoGetDTO> listarProductosNombre(String nombre);

    List<ProductoGetDTO> listarProductosPrecio(float precioMinimo, float precioMaximo);
}
