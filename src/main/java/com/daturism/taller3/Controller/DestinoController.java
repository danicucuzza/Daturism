package com.daturism.taller3.Controller;

import com.daturism.taller3.Model.Destino;
import com.daturism.taller3.Service.DestinoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/destinos")
public class DestinoController {
    private final DestinoService destinoService;

    public DestinoController(DestinoService destinoService) {
        this.destinoService = destinoService;
    }

    @PostMapping
    public Destino crearDestino(@RequestBody Destino destino){
        return destinoService.crearDestino(destino);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destino> obtenerDestino(@PathVariable Long id) {
        Optional<Destino> destino = destinoService.obtenerDestino(id);
        return destino.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Destino> obtenerTodosLosDestinos() {
        return destinoService.obtenerTodosLosDestinos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destino> actualizarDestino(@PathVariable Long id, @RequestBody Destino destino) {
        if (!destinoService.obtenerDestino(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        destino.setId(id);
        return ResponseEntity.ok(destinoService.crearDestino(destino));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDestino(@PathVariable Long id) {
        if (!destinoService.obtenerDestino(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        destinoService.eliminarDestino(id);
        return  ResponseEntity.noContent().build();
    }
}
