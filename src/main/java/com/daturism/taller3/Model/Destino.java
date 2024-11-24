package com.daturism.taller3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_destino;
    private String nombre;
    private String descripcion;
    private double precio;
    private String ubicacion;
    private String imagenUrl;

    @ManyToOne
    @JoinColumn (name = "id_paquete")
    @JsonIgnore
    private Paquete paquete;

    public Destino() {
    }

    public Destino(Long id_destino, String nombre, String descripcion, double precio, String ubicacion, String imagenUrl, Paquete paquete) {
        this.id_destino = id_destino;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.imagenUrl = imagenUrl;
        this.paquete = paquete;
    }
}
