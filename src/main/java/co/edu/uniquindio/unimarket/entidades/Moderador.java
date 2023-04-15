package co.edu.uniquindio.unimarket.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class Moderador extends Persona implements Serializable{

    @ToString.Exclude
    @OneToMany (mappedBy = "moderador")
    private List<ProductoModerador> productosModerador;

    /*@Builder
    //public Moderador(String nombre, String email, String password) {
        super(nombre, email, password);
    }
    */
}
