package co.edu.uniquindio.unimarket.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Positive
    @Column(nullable = false)
    private float valorTotal;

    @ToString.Exclude
    @OneToMany (mappedBy = "compra")
    private List<DetalleCompra> detalleCompras;

    @ManyToOne
    @JoinColumn (nullable = false)
    private Usuario usuario;

    @Enumerated (EnumType.STRING)
    @Column(nullable = false)
    private MetodoPago metodoPago;

    @Builder
    public Compra(float valorTotal, Usuario usuario, MetodoPago metodoPago) {
        this.valorTotal = valorTotal;
        this.usuario = usuario;
        this.metodoPago = metodoPago;
        this.fechaCreacion = LocalDateTime.now();
    }
}
