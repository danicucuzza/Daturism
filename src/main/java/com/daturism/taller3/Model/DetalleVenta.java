package com.daturism.taller3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_venta")
    private Long id;

    @Column
    private int cantidad;

    @Column
    private BigDecimal precio;

    @ManyToOne
    @JoinColumn(name = "id_paquete")
    private Paquete paquete;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;
}
