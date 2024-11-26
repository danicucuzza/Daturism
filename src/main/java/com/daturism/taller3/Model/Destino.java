package com.daturism.taller3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "destinos")
public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_destino;

    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int maxPeople;
    private int duration;
    private String ubicacion;
    private String imagenUrl;

    @ManyToOne
    @JoinColumn (name = "id_paquete")
    @JsonIgnore
    private Paquete paquete;

    public Destino(String nombre, String descripcion, BigDecimal precio, String ubicacion, String imagenUrl) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.imagenUrl = imagenUrl;
    }
}
