package com.daturism.taller3.Controller;

import com.daturism.taller3.Model.Paquete;
import com.daturism.taller3.Service.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paquetes")
public class PaqueteController {

    @Autowired
    private PaqueteService iPaqueteService;

    @PostMapping("/crear")
    public String crearPaquete(@RequestBody Paquete paquete) {
        iPaqueteService.savePaquete(paquete);
        return "El paquete fue creado correctamente";
    }

    @GetMapping("/{id}")
    public Paquete obtenerPaquete(@PathVariable Long id) {
        return iPaqueteService.findPaquete(id);
    }

    @GetMapping("/traertodos")
    public List<Paquete> getPaquetes() {
        return iPaqueteService.getPaquetes();
    }

    @PutMapping("/editar")
    public Paquete editPaquete(@RequestBody Paquete paquete){
        iPaqueteService.editPaquete(paquete);
        return iPaqueteService.findPaquete(paquete.getId_paquete());
    }

    @DeleteMapping("/eliminar/{id}")
    public String deletePaquete(@PathVariable Long id_paquete) {
        iPaqueteService.deletePaquete(id_paquete);
        return "El paquete fue eliminado correctamente";
    }

    @GetMapping("/buscar/{palabra}")
    public List<Paquete> findPaqueteByName(@RequestParam String palabra) {
        return iPaqueteService.findPaqueteByName(palabra);
    }

    @PostMapping("/asociardestino/{id_paquete}")
    public Paquete addDestinoInPaquete(@PathVariable Long id_paquete, @RequestBody List<Long> destinoIds) {
        return iPaqueteService.addDestinosInPaquete(id_paquete, destinoIds);
    }
}
