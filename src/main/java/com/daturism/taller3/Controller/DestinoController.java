package com.daturism.taller3.Controller;

import com.daturism.taller3.Model.Destino;
import com.daturism.taller3.Service.DestinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/destinos")
public class DestinoController {

    @Autowired
    private DestinoService iDestinoService;

    @PostMapping("/crear")
    public Destino createDestino(@RequestBody Destino destino) {
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

    @GetMapping("/buscar/{palabra}")
    public List<Destino> findDestinoByName(@RequestParam String palabra) {
        return iDestinoService.findDestinoByName(palabra);
    }
}

//    @PostMapping("/{id}/asignardestinos/{paqueteId}")
//    public Paquete asignarDestinos(@PathVariable Long id, @PathVariable Long paqueteId, @RequestBody List<Long> destinoIds) {
//        Paquete paquete = paqueteService.findPaquete(paqueteId);
//        List<Destino> destinos = destinoService.getDestinosByIds(destinoIds);
//        paquete.setListaDeDestinos(destinos);
//        paqueteService.savePaquete(paquete);
//        return paquete;
//    }
