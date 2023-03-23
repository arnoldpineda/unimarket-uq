package co.edu.uniquindio.unimarket.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Length(max = 100)
    @NotNull
    @Column(length = 100, nullable = false)
    private String nombre;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer unidades;

    @NotNull
    @Lob
    @Column(nullable = false)
    private String descripcion;

    @NotNull
    @Positive
    @Column(nullable = false)
    private float precio;

    @NotNull
    @Column(nullable = false)
    private boolean activo;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime fechaCreado;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime fechaLimite;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> imagen;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private List<Categoria> categoria;

    @ToString.Exclude
    @OneToMany (mappedBy = "producto")
    private List<DetalleCompra> detalleCompras;

    @ToString.Exclude
    @OneToMany (mappedBy = "producto")
    private List<Comentario> comentarios;

    @ToString.Exclude
    @OneToMany (mappedBy = "producto")
    private List<ProductoModerador> productosModerador;

    @ManyToOne
    @JoinColumn (nullable = false)
    private Usuario usuario;

    //Lista favorito
    @ManyToMany
    //@JoinTable (name = "favoritos")
    private List<Usuario> usuarios;

    @Builder
    public Producto(Integer codigo, String nombre, Integer unidades, String descripcion, float precio, Usuario usuario, Map<String, String> imagen, List<Categoria> categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.usuario = usuario;
        this.activo = false;
        this.fechaCreado = LocalDateTime.now();
        this.fechaLimite = fechaCreado;
        this.imagen = imagen;
        this.categoria = categoria;
    }
}
