package com.daturism.taller3.Controller;

import com.daturism.taller3.Model.Paquete;
import com.daturism.taller3.Service.PaqueteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paquetes")
public class PaqueteController {
    private final PaqueteService paqueteService;

    public PaqueteController(PaqueteService paqueteService) {
        this.paqueteService = paqueteService;
    }

    @PostMapping
    public Paquete crearPaquete(@RequestBody Paquete paquete) {
        return paqueteService.crearPaquete(paquete);
    }

    @PostMapping("/{paqueteId}/destino/{destinoId}")
    public ResponseEntity<Paquete> asignarDestino(@PathVariable Long paqueteId, @PathVariable Long destinoId) {
        try {
            Paquete paquete = paqueteService.asignarDestino(paqueteId, destinoId);
            return ResponseEntity.ok(paquete);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paquete> obtenerPaquete(@PathVariable Long id) {
        Optional<Paquete> paquete = paqueteService.obtenerPaquete(id);
        return  paquete.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Paquete> obtenerTodosLosPaquetes() {
        return paqueteService.obtenerTodosLosPaquetes();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paquete> actualizarPaquete(@PathVariable Long id, @RequestBody Paquete paquete) {
        if (!paqueteService.obtenerPaquete(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        paquete.setId(id);
        return ResponseEntity.ok(paqueteService.crearPaquete(paquete));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaquete(@PathVariable Long id) {
        if (!paqueteService.obtenerPaquete(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        paqueteService.eliminarPaquete(id);
        return ResponseEntity.noContent().build();
    }
}
