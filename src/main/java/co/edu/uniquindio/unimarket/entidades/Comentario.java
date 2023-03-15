package co.edu.uniquindio.unimarket.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    private String mensaje;
    private LocalDateTime fechaCreacion;


    public Comentario() {
    }

    public Comentario(Integer codigo, String mensaje, LocalDateTime fechaCreacion) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
    }
}
