package com.daturism.taller3.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter @Setter
@Entity
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_paquete;
    private String nombre;
    private String descripcion;
    private double precioTotal;
    private String duracion;

    @OneToMany (mappedBy = "paquete")
    private List<Destino> listaDeDestinos;

    @ManyToOne
    @JoinColumn (name = "id_cliente")
    @JsonIgnore
    Cliente cliente;

    public Paquete() {
    }

    public Paquete(Long id_paquete, String nombre, String descripcion, double precioTotal, String duracion, List<Destino> listaDeDestinos) {
        this.id_paquete = id_paquete;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioTotal = precioTotal;
        this.duracion = duracion;
        this.listaDeDestinos = listaDeDestinos;
    }

    public Paquete(String s, String s1) {
    }
}
