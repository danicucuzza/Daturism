package com.daturism.taller3.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter @Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_paquete;

    private String nombre;
    private String descripcion;
    private BigDecimal precioTotal;
    private String duracion;
    private String imagenUrl;

    @OneToMany(mappedBy = "paquete", cascade = CascadeType.ALL)
    private List<Destino> listaDeDestinos;

    @ManyToOne
    @JoinColumn (name = "id_cliente")
    @JsonIgnore
    Cliente cliente;

    public Paquete(String nombre, String descripcion, String duracion, String imagenUrl, List<Destino> listaDeDestinos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.imagenUrl = imagenUrl;
        this.listaDeDestinos = listaDeDestinos;
    }
}
