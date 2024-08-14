package com.daturism.taller3.Controller;

import com.daturism.taller3.Model.Reserva;
import com.daturism.taller3.Service.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public Reserva crearReserva(@RequestBody Reserva reserva) {
        return reservaService.crearReserva(reserva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerReserva(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaService.obtenerReserva(id);
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Reserva> obtenerTodasLasReservas() {
        return reservaService.obtenerTodasLasReservas();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizarReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        if (!reservaService.obtenerReserva(id).isPresent()) {
            return  ResponseEntity.notFound().build();
        }
        reserva.setId(id);
        return ResponseEntity.ok(reservaService.crearReserva(reserva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {
        if (!reservaService.obtenerReserva(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reservaService.eliminarReserva(id);
        return ResponseEntity.noContent().build();
    }
}
