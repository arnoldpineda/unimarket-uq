package co.edu.uniquindio.unimarket.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    private String nombre;
    private Integer unidades;
    private String descripcion;
    private float precio;
    private boolean activo;
    private LocalDateTime fechaCreado;
    private LocalDateTime fechaLimite;


    public Producto() {
    }

    public Producto(Integer codigo, String nombre, Integer unidades, String descripcion, float precio, boolean activo, LocalDateTime fechaCreado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.activo = activo;
        this.fechaCreado = fechaCreado;
    }
}
