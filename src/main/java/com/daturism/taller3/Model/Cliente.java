package com.daturism.taller3.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter @Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Usuario{

    private String tipoCliente;

    @OneToMany(mappedBy = "cliente")
    private List<Paquete> listaDePaquetes;

    @OneToMany
    @JoinColumn(name = "cliente_id")
    private List<Paquete> carrito = new ArrayList<>();

    private int cantidadDePaquetes;
}
