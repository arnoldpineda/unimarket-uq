package co.edu.uniquindio.unimarket.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class DetalleCompra implements Serializable {

    @Id
    private Integer codigo;

    private Integer unidades;
    private float precioProducto;
}
