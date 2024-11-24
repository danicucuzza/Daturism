package com.daturism.taller3.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String email;

    private String password;

    private String telefono;

    private String direccion;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String email, String password, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.direccion = direccion;
    }
}
