package com.daturism.taller3.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter @Setter
@Entity
public class Cliente extends Usuario{

    private String tipoCliente;

    @OneToMany(mappedBy = "cliente")
    private List<Paquete> listaDePaquetes;

    @OneToMany
    @JoinColumn(name = "cliente_id")
    private List<Paquete> carrito = new ArrayList<>();

    private int cantidadDePaquetes;

    public Cliente() {
        this.carrito = new ArrayList<>();
        this.cantidadDePaquetes = 0;
    }

    public Cliente(Long id, String nombre, String email, String password, String telefono, String direccion, String tipoCliente) {
        super(id, nombre, email, password, telefono, direccion);
        this.tipoCliente = tipoCliente;
        this.carrito = new ArrayList<>();
        this.cantidadDePaquetes = 0;
    }

    public void addToCart(Paquete paquete) {
        this.carrito.add(paquete);
        this.cantidadDePaquetes++;
    }

    public void removeFromCart(Paquete paquete) {
        this.carrito.remove(paquete);
        this.cantidadDePaquetes--;
    }
}
