package com.daturism.taller3.Model;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
