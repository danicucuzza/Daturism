package com.daturism.taller3.Controller;

import com.daturism.taller3.Model.Cliente;
import com.daturism.taller3.Model.Destino;
import com.daturism.taller3.Model.Paquete;
import com.daturism.taller3.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private IClienteService iClienteService;
    @Autowired
    private IPaqueteService iPaqueteService;
    @Autowired
    private IDestinoService iDestinoService;


    @PostMapping("/crear")
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        iClienteService.saveCliente(cliente);
        return cliente;
    }

    @GetMapping("/{id}")
    public Cliente obtenerCliente(@PathVariable Long id) {
        return iClienteService.findCliente(id);
    }

    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        iClienteService.editCliente(cliente);
        return cliente;
    }

    @DeleteMapping("/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        iClienteService.deleteCliente(id);
        return "Cliente eliminado correctamente";
    }

    @PostMapping("/{id}/asignardestinos/{paqueteId}")
    public Paquete asignarDestinos(@PathVariable Long id, @PathVariable Long paqueteId, @RequestBody List<Long> destinoIds) {
        Paquete paquete = iPaqueteService.findPaquete(paqueteId);
        List<Destino> destinos = iDestinoService.getDestinosByIds(destinoIds);
        paquete.setListaDeDestinos(destinos);
        iPaqueteService.savePaquete(paquete);
        return paquete;
    }

//    @PostMapping("/agregarpaquete/{id_cliente}")
//    public Paquete addPaqueteInCliente(@PathVariable Long id, @RequestBody List<Long> paqueteIds) {
//        return iPaqueteService.addDestinosInPaquete(id, paqueteIds);
//    }

    @PostMapping("/agregarpaquete/{id_cliente}")
    public Cliente addPaqueteInCliente(@PathVariable Long id_cliente, @RequestBody List<Long> paqueteIds) {
        Cliente cliente = iClienteService.findCliente(id_cliente);

        for (Long paqueteId : paqueteIds) {
            Paquete paquete = iPaqueteService.findPaquete(paqueteId);
            paquete.setCliente(cliente); // Asocia el cliente al paquete
            cliente.getListaDePaquetes().add(paquete); // Añade el paquete al cliente
            iPaqueteService.savePaquete(paquete); // Guarda el paquete actualizado
        }

        iClienteService.saveCliente(cliente); // Guarda el cliente con los paquetes asignados
        return cliente;
    }


    @PostMapping("/{id}/comprar")
    public String comprarPaquete(@PathVariable Long id_cliente, @RequestBody Long paquetesIds) {
        Cliente cliente = iClienteService.findCliente(id_cliente);
        Paquete paquete = iPaqueteService.findPaquete(paquetesIds);
        cliente.getListaDePaquetes().add(paquete); // Implementar lógica de compra aquí
        iClienteService.saveCliente(cliente);
        return "Compra realizada correctamente";
    }

    @GetMapping("/traertodos")
    public List<Cliente> obtenerClientes() {
        return iClienteService.getClientes();
    }
}
