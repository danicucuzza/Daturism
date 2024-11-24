package com.daturism.taller3.Model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Administrador extends Usuario{

    private String nivelDeAcceso;

    public Administrador() {
    }

    public Administrador(Long id, String nombre, String email, String password, String telefono, String direccion, String nivelDeAcceso) {
        super(id, nombre, email, password, telefono, direccion);
        this.nivelDeAcceso = nivelDeAcceso;
    }
}
