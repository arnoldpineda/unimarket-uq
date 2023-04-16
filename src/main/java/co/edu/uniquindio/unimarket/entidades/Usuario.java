package co.edu.uniquindio.unimarket.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.List;

@Entity
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class Usuario extends Persona implements Serializable{

    @Length(max = 100)
    @NotNull
    @Column(length = 100, nullable = false)
    private String direccion;

    @Length(max = 10)
    @NotNull
    @Column(length = 10, nullable = false)
    private String telefono;

    @ToString.Exclude
    @OneToMany (mappedBy = "usuario")
    private List<Compra> compras;

    @ToString.Exclude
    @OneToMany (mappedBy = "vendedor")
    private List<Producto> productos;

    @ToString.Exclude
    @OneToMany (mappedBy = "usuario")
    private List<Comentario> comentarios;

    //Lista favorito
    @ManyToMany
    @ToString.Exclude
    @JoinTable (name = "favoritos")
    private List<Producto> favoritos;

    @ToString.Exclude
    @OneToMany (mappedBy = "usuario")
    private List<Queja> quejas;

    public Usuario(String nombre, String email, String password, String direccion, String telefono) {
        super(nombre, email, password);
        this.direccion = direccion;
        this.telefono = telefono;
    }
}
