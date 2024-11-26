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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "paquetes")
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_paquete")
    private Long id_paquete;

    private String nombre;
    private String descripcion;
    private BigDecimal precioTotal;
    private String duracion;
    private String imagenUrl;
    private int stock;

    @OneToMany(mappedBy = "paquete", cascade = CascadeType.ALL)
    private List<Destino> listaDeDestinos;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item item;

    @JsonIgnore
    @OneToMany(mappedBy = "paquete", cascade = CascadeType.REMOVE)
    private List<DetalleVenta> detalleVentaList;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Paquete(String nombre, String descripcion, String duracion, String imagenUrl) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.imagenUrl = imagenUrl;
    }
}
