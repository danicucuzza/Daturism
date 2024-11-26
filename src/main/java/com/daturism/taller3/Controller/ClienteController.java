package com.daturism.taller3.Controller;

import com.daturism.taller3.Config.SecurityConfig;
import com.daturism.taller3.Model.Cliente;
import com.daturism.taller3.Model.Destino;
import com.daturism.taller3.Model.Paquete;
import com.daturism.taller3.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private PasswordEncoder passwordEncorder;

    @PostMapping("/registro")
    public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente cliente) {
        if (cliente.getRole() == null) {
            cliente.setRole(Cliente.Role.CLIENTE);
        }

        cliente.setPassword(passwordEncorder.encode(cliente.getPassword()));

        clienteService.saveCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/registro-admin")
    public ResponseEntity<Cliente> registrarAdmin(@RequestBody Cliente cliente) {
        cliente.setRole(Cliente.Role.ADMIN);
        cliente.setPassword(passwordEncorder.encode(cliente.getPassword()));

        clienteService.saveCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{id}")
    public Cliente obtenerCliente(@PathVariable Long id) {
        return clienteService.findCliente(id);
    }

    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        clienteService.editCliente(cliente);
        return cliente;
    }

    @DeleteMapping("/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return "Cliente eliminado correctamente";
    }

//    @PostMapping("/{id}/asignardestinos/{paqueteId}")
//    public Paquete asignarDestinos(@PathVariable Long id, @PathVariable Long paqueteId, @RequestBody List<Long> destinoIds) {
//        Paquete paquete = paqueteService.findPaquete(paqueteId);
//        List<Destino> destinos = destinoService.getDestinosByIds(destinoIds);
//        paquete.setListaDeDestinos(destinos);
//        paqueteService.savePaquete(paquete);
//        return paquete;
//    }
}
