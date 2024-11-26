package com.daturism.taller3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetalleVentaDTO {

    private String paquete;
    private int cantidad;
    private BigDecimal precio;
    private BigDecimal precioTotal;
    private Long id_venta;
    private String item;
}
