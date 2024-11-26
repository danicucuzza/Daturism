package com.daturism.taller3.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class VentaDetalleDTO {

    private LocalDate fecha;
    private Map<String, List<DetalleVentaDTO>> paquete = new HashMap<String, List<DetalleVentaDTO>>();
    private BigDecimal precioTotal;
}
