package com.daturism.taller3.dto;

import com.daturism.taller3.Model.Destino;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PaqueteDestinoDTO {

    private String nombrePaquete;
    private List<Destino> listaDestinos;

    public PaqueteDestinoDTO() {
    }

    public PaqueteDestinoDTO(String nombrePaquete, List<Destino> listaDestinos) {
        this.nombrePaquete = nombrePaquete;
        this.listaDestinos = listaDestinos;
    }
}
