package com.daturism.taller3.Service;

import com.daturism.taller3.Model.DetalleVenta;
import com.daturism.taller3.Repository.IDetalleVentaRepository;
import com.daturism.taller3.Repository.IPaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaService {

    @Autowired
    private IDetalleVentaRepository detalleVentaRepository;

    @Autowired
    private IPaqueteRepository paqueteRepository;

    public List<DetalleVenta> getDetalle() {
        List<DetalleVenta> listaDetalle = detalleVentaRepository.findAll();
        return listaDetalle;
    }

    public void saveDetalle(DetalleVenta detalleVenta) {
        detalleVentaRepository.save(detalleVenta);
    }

    public DetalleVenta findDetalle(Long id_detalle) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id_detalle).orElse(null);
        return detalleVenta;
    }

    public void deleteDetalleVenta (Long id_detalle) {
        detalleVentaRepository.deleteById(id_detalle);
    }

    public void editDetalleVenta(DetalleVenta detalleVenta) {
        this.saveDetalle(detalleVenta);
    }
}
