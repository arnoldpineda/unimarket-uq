package co.edu.uniquindio.unimarket.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @NotNull
    @Lob
    @Column(nullable = false)
    private String mensaje;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    //Quitar los constructores
    /*@Builder
    public Comentario(String mensaje, Producto producto, Usuario usuario) {
    this.mensaje = mensaje;
    this.producto = producto;
    this.usuario = usuario;
    this.fechaCreacion = LocalDateTime.now();
    }*/
}
