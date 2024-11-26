package com.daturism.taller3.Repository;

import com.daturism.taller3.Model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}
