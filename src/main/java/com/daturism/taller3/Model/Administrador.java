package com.daturism.taller3.Model;

import jakarta.persistence.Entity;
import lombok.*;

@Getter @Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Administrador extends Usuario{

    private String nivelDeAcceso;
}
