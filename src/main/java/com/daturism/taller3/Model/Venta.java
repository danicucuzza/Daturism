package com.daturism.taller3.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private long id;

    @Column
    private LocalDate date;

    @Column
    private BigDecimal price;

    @Column
    private String state;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;


    @OneToMany(mappedBy = "venta", cascade = CascadeType.REMOVE)
    private List<DetalleVenta> detalleVentaList;
}