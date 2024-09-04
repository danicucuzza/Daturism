package com.daturism.taller3.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Entity
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_paquete;
    private String nombre;
    private String descripcion;
    private double precio;
    private String duracion;

    @OneToMany (mappedBy = "paquete")
    private List<Destino> listaDeDestinos;

    public Paquete() {
    }

    public Paquete(Long id_paquete, String nombre, String descripcion, double precio, String duracion, List<Destino> listaDeDestinos) {
        this.id_paquete = id_paquete;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracion = duracion;
        this.listaDeDestinos = listaDeDestinos;
    }
}
