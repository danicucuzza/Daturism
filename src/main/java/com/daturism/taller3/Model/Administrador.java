package com.daturism.taller3.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "admins")
public class Administrador{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String email;

    private String password;
    private String nivelDeAcceso;
}
