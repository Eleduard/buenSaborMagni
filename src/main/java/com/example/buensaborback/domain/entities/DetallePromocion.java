package com.example.buensaborback.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class DetallePromocion extends Base {

    private Integer cantidadArticulos;

    //@Id
    @ManyToOne
    @JoinColumn(name = "articulo_id", nullable = false)
    private Articulo articulo;

    //@Id
    @ManyToOne
    @JoinColumn(name = "promocion_id", nullable = false)
    private Promocion promocion;


}
