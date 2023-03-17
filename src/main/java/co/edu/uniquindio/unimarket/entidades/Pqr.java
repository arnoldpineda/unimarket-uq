package co.edu.uniquindio.unimarket.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Pqr implements Serializable {

    @Id
    private Integer codigo;

    private LocalDateTime fechaPublicacion;
    private String descripcion;

}
