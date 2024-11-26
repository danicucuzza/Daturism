package com.daturism.taller3.dto;

import com.daturism.taller3.Model.Cliente;
import com.daturism.taller3.Model.DetalleVenta;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class VentaDTO {
    private LocalDate fecha;
    private BigDecimal precio;
    private Cliente cliente;
    private List<DetalleVenta> detalleVentaList;
}
