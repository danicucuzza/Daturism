package com.daturism.taller3.Controller;

import com.daturism.taller3.Model.Cliente;
import com.daturism.taller3.Model.Destino;
import com.daturism.taller3.Model.Paquete;
import com.daturism.taller3.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdministradorController {

    @Autowired
    private IClienteService iClienteService;
    @Autowired
    private IPaqueteService iPaqueteService;
    @Autowired
    private IDestinoService iDestinoService;

    // Métodos para gestionar Clientes
    @PostMapping("/clientes/crear")
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        iClienteService.saveCliente(cliente);
        return cliente;
    }

    @GetMapping("/clientes/{id}")
    public Cliente obtenerCliente(@PathVariable Long id) {
        return iClienteService.findCliente(id);
    }

    @PutMapping("/clientes/{id}")
    public Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        iClienteService.editCliente(cliente);
        return cliente;
    }

    @DeleteMapping("/clientes/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        iClienteService.deleteCliente(id);
        return "Cliente eliminado correctamente";
    }





    // Métodos para gestionar Paquetes
    @PostMapping("/paquetes/crear")
    public Paquete crearPaquete(@RequestBody Paquete paquete) {
        iPaqueteService.savePaquete(paquete);
        return paquete;
    }

    @GetMapping("/paquetes/{id}")
    public Paquete obtenerPaquete(@PathVariable Long id) {
        return iPaqueteService.findPaquete(id);
    }

    @PutMapping("/paquetes/{id}")
    public Paquete actualizarPaquete(@PathVariable Long id, @RequestBody Paquete paquete) {
        paquete.setId_paquete(id);
        iPaqueteService.editPaquete(paquete);
        return paquete;
    }

    @DeleteMapping("/paquetes/{id}")
    public String eliminarPaquete(@PathVariable Long id) {
        iPaqueteService.deletePaquete(id);
        return "Paquete eliminado correctamente";
    }




    // Métodos para gestionar Destinos
    @PostMapping("/destinos/crear")
    public Destino crearDestino(@RequestBody Destino destino) {
        iDestinoService.saveDestino(destino);
        return destino;
    }

    @GetMapping("/destinos/{id}")
    public Destino obtenerDestino(@PathVariable Long id) {
        return iDestinoService.findDestino(id);
    }

    @PutMapping("/destinos/{id}")
    public Destino actualizarDestino(@PathVariable Long id, @RequestBody Destino destino) {
        destino.setId_destino(id);
        iDestinoService.editDestino(destino);
        return destino;
    }

    @DeleteMapping("/destinos/{id}")
    public String eliminarDestino(@PathVariable Long id) {
        iDestinoService.deleteDestino(id);
        return "Destino eliminado correctamente";
    }
}
