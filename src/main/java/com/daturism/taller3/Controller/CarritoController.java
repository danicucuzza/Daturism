package com.daturism.taller3.Controller;

import com.daturism.taller3.Model.Cliente;
import com.daturism.taller3.Model.Paquete;
import com.daturism.taller3.Service.IClienteService;
import com.daturism.taller3.Service.IPaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private IClienteService iClienteService;
    @Autowired
    private IPaqueteService iPaqueteService;

    @PostMapping("/{id_cliente}/agregar")
    public Cliente addToCart(@PathVariable Long id_cliente, @RequestBody Long paqueteId) {
        Cliente cliente = iClienteService.findCliente(id_cliente);
        Paquete paquete = iPaqueteService.findPaquete(paqueteId);
        iClienteService.addToCart(cliente.getId(), paquete);
        return iClienteService.findCliente(id_cliente);
    }

    @PostMapping("/{id_cliente}/eliminar")
    public Cliente removeFromCart(@PathVariable Long id_cliente, @RequestBody Long paqueteId){
        Cliente cliente = iClienteService.findCliente(id_cliente);
        Paquete paquete = iPaqueteService.findPaquete(paqueteId);
        iClienteService.removeFromCart(cliente.getId(), paquete);
        return iClienteService.findCliente(id_cliente);
    }
    
    @PostMapping("/{id_cliente}/comprar")
    public String comprarPaquetes(@PathVariable Long id_cliente, @RequestBody List<Long> paqueteIds) {
        Cliente cliente = iClienteService.findCliente(id_cliente);
        for (Long id_paquete : paqueteIds) {
            Paquete paquete = iPaqueteService.findPaquete(id_paquete);
            iClienteService.removeFromCart(cliente.getId(), paquete);
            cliente.getListaDePaquetes().add(paquete);
            iPaqueteService.savePaquete(paquete);
        }
        iClienteService.saveCliente(cliente);
        return "Compra realizada correctamente";
    }
}
