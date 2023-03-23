package co.edu.uniquindio.unimarket.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.Banner;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ProductoModerador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Length(max = 50)
    @NotNull
    @Column(length = 50, nullable = false)
    private String motivo;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Moderador moderador;

    @Enumerated (EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Builder
    public ProductoModerador(String motivo, Producto producto, Moderador moderador, Estado estado) {
        this.motivo = motivo;
        this.producto = producto;
        this.moderador = moderador;
        this.estado = estado;
        this.fecha = LocalDateTime.now();
    }
}
