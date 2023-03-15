package co.edu.uniquindio.unimarket.entidades;

import jakarta.persistence.Entity;
import lombok.ToString;

import java.io.Serializable;

public class Usuario extends Persona{

    private String direccion;
    private String telefono;


}
