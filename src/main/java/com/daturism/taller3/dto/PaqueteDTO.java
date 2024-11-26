package com.daturism.taller3.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaqueteDTO {

    private String nombre;
    private String descripcion;
    private Long sku;
    private int stock;
    private BigDecimal precio;
    private String item;
}
