package com.daturism.taller3.Service;

import com.daturism.taller3.Model.Cliente;
import com.daturism.taller3.Model.Venta;
import com.daturism.taller3.Repository.IDetalleVentaRepository;
import com.daturism.taller3.Repository.IVentaRepository;
import com.daturism.taller3.dto.DetalleVentaDTO;
import com.daturism.taller3.dto.VentaDetalleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VentaService {

    @Autowired
    private IVentaRepository ventaRepository;

    @Autowired
    private IDetalleVentaRepository detalleVentaRepository;

    public Venta findVentaById(Long id_venta) {
        Venta venta  = ventaRepository.findById(id_venta).orElseThrow(() -> new RuntimeException("Venta invalida"));
        return venta;
    }

    public void saveVenta(Venta venta) {
        ventaRepository.save(venta);
    }

    public void deleteVenta(Long id_venta) {
        ventaRepository.deleteById(id_venta);
    }

    public void editVenta(Venta venta) {
        this.saveVenta(venta);
    }

    //Obtenemos el detalle de una compra
    public VentaDetalleDTO findDetalleVenta(Long id_venta) {
        Venta venta = ventaRepository.findById(id_venta).orElseThrow(() -> new RuntimeException("Venta invalida"));
        VentaDetalleDTO detalle = new VentaDetalleDTO();
        detalle.setFecha(venta.getDate());
        Map<String, List<DetalleVentaDTO>> paquete = new HashMap<String, List<DetalleVentaDTO>>();
        List<DetalleVentaDTO> listDto = new ArrayList<>();
        venta.getDetalleVentaList().stream().forEach(p->listDto.add
                (new DetalleVentaDTO(p.getPaquete().getDescripcion(),p.getCantidad(),p.getPrecio(),p.getPrecio().multiply(BigDecimal.valueOf(p.getCantidad())),p.getVenta().getId(),p.getPaquete().getItem().getDescription())));
        venta.getDetalleVentaList().stream()
                .map(p -> p.getPaquete().getItem().getDescription())
                .distinct().forEach(p -> paquete.put(p,listDto.stream().filter(u -> u.getItem().equals(p)).collect(Collectors.toList())));

        detalle.setPaquete(paquete);
        detalle.setPrecioTotal(venta.getPrice());

        return detalle;
    }
}
