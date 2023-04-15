package co.edu.uniquindio.unimarket.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@MappedSuperclass
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @NotNull
    @Length(max = 50)
    @Column(nullable = false, length = 50)
    private String nombre;

    @NotNull
    @Length(max = 50)
    @Email
    @Column(nullable = false, length = 50, unique=true)
    private String email;

    @NotNull
    @Length(max = 100)
    @ToString.Exclude
    @Column(nullable = false, length = 100)
    private String password;

    public Persona(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }
}
