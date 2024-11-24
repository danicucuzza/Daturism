package com.daturism.taller3.Controller;

import com.daturism.taller3.Model.Cliente;
import com.daturism.taller3.Model.Destino;
import com.daturism.taller3.Service.IDestinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/destinos")
public class DestinoController {

    @Autowired
    private IDestinoService iDestinoService;

    @PostMapping("/crear")
    public Destino createDestino(@RequestBody Destino destino){
        iDestinoService.saveDestino(destino);
        return destino;
    }

    @GetMapping("/{id}")
    public Destino obtenerDestino(@PathVariable Long id) {
        return iDestinoService.findDestino(id);
    }

    @GetMapping("/traertodos")
    public List<Destino> getDestinos() {
        return iDestinoService.getDestinos();
    }

    @DeleteMapping("/eliminar/{id}")
    public String deleteDestino(@PathVariable Long id_destino) {
        iDestinoService.deleteDestino(id_destino);
        return "El destino fue eliminado correctamente";
    }

    @PutMapping("/editar")
    public Destino editDestino(@RequestBody Destino destino) {
        iDestinoService.editDestino(destino);
        return iDestinoService.findDestino(destino.getId_destino());
    }

//    @GetMapping("/traerids")
//    public List<Destino> getDestinosByIds(@RequestParam List<Long> ids) {
//        return iDestinoService.getDestinosByIds(ids);
//    }

    @GetMapping("/buscar/{palabra}")
    public List<Destino> findDestinoByName(@RequestParam String palabra) {
        return iDestinoService.findDestinoByName(palabra);
    }
}