package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    protected String denominacion;
    protected Double precioVenta;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "articulo")
    @Builder.Default
    protected Set<Imagen> imagenes = new HashSet<Imagen>();

    @ManyToOne
    @JoinColumn(name = "unidad_medida_id", nullable = false)
    protected UnidadMedida unidadMedida;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    protected Categoria categoria;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "articulo")
    @Builder.Default
    private Set<DetallePedido> detallePedidos = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "articulo")
    @Builder.Default
    private Set<DetallePromocion> detallesPromocion = new HashSet<>();

}
