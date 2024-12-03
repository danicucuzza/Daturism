package com.daturism.taller3.Controller;


import com.daturism.taller3.Model.Cliente;
import com.daturism.taller3.Model.Role;
import com.daturism.taller3.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClienteController(ClienteService clienteService, PasswordEncoder passwordEncoder) {
        this.clienteService = clienteService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registro")
    @ResponseBody
    public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente cliente) {
        if (cliente.getRole() == null) {
            cliente.setRole(Role.CLIENTE);
        }
        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        Cliente createdCliente = clienteService.saveCliente(cliente);
        return ResponseEntity.ok(createdCliente);
    }

    @GetMapping("/buscar/{id}")
    public Cliente obtenerCliente(@PathVariable Long id) {
        return clienteService.findCliente(id);
    }

    @PutMapping("/actualizar/{id}")
    public Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        clienteService.editCliente(cliente);
        return cliente;
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return "Cliente eliminado correctamente";
    }

    @GetMapping("/buscar-por-email/{email}")
    public ResponseEntity<Cliente> obtenerClientePorEmail(@PathVariable String email) {
        Cliente cliente = clienteService.findByEmail(email);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.notFound().build();
    }
}
