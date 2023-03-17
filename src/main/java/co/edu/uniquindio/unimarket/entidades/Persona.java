package co.edu.uniquindio.unimarket.entidades;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
public class Persona implements Serializable {

    @Id
    private Integer codigo;

    private String nombre;
    private String email;
    private String password;

}
