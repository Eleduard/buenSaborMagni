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

    private Integer cantidad;


    @ManyToOne
    private Articulo articulo;


    @ManyToOne
    private Promocion promocion;


}
