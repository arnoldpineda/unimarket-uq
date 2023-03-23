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
public class Queja implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime fechaPublicacion;

    @NotNull
    @Lob
    @Column(nullable = false)
    private String descripcion;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @Builder
    public Queja(String descripcion, Usuario usuario) {
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.fechaPublicacion = LocalDateTime.now();
    }
}
