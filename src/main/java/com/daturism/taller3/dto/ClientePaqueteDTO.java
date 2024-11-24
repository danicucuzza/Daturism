package com.daturism.taller3.dto;

import com.daturism.taller3.Model.Paquete;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class ClientePaqueteDTO {

    private String nombreCliente;
    private String emailCliente;
    private Set<Paquete> paquetesAdquiridos;

    public ClientePaqueteDTO() {
    }

    public ClientePaqueteDTO(String nombreCliente, String emailCliente, Set<Paquete> paquetesAdquiridos) {
        this.nombreCliente = nombreCliente;
        this.emailCliente = emailCliente;
        this.paquetesAdquiridos = paquetesAdquiridos;
    }
}
