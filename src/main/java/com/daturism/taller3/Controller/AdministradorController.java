//package com.daturism.taller3.Controller;
//import com.daturism.taller3.Model.*;
//import com.daturism.taller3.Service.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/admin")
//public class AdministradorController {
//
//    @Autowired
//    private ClienteService iClienteService;
//    @Autowired
//    private PaqueteService iPaqueteService;
//    @Autowired
//    private DestinoService iDestinoService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private AdministradorService administradorService;
//
//    // Métodos para gestionar Clientes
//    @PostMapping("/registro")
//    public Cliente crearCliente(@RequestBody Cliente cliente) {
//        // Establecer rol de admin si no está definido
//        if (cliente.getRole() == null) {
//            cliente.setRole(Role.CLIENTE);
//        }
//
//        // Encriptar contraseña
//        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
//
//        iClienteService.saveCliente(cliente);
//        return cliente;
//    }
//
//    @PostMapping("/crearadmin")
//    public Administrador registrarAdmin(@RequestBody Administrador admin) {
//        if (admin.getRole() == null) {
//            admin.setRole(Role.ADMIN);
//        }
//
//        // Encriptar contraseña
//        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
//
//        administradorService.saveAdmin(admin);
//        return admin;
//    }
//
//    @GetMapping("/clientes/{id}")
//    public Cliente obtenerCliente(@PathVariable Long id) {
//        return iClienteService.findCliente(id);
//    }
//
//    @PutMapping("/clientes/{id}")
//    public Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
//        Cliente clienteExistente = iClienteService.findCliente(id);
//
//        // Actualizar campos
//        clienteExistente.setNombre(cliente.getNombre());
//        clienteExistente.setEmail(cliente.getEmail());
//        clienteExistente.setTelefono(cliente.getTelefono());
//        clienteExistente.setDireccion(cliente.getDireccion());
//
//        // Si se proporciona nueva contraseña, encriptarla
//        if (cliente.getPassword() != null && !cliente.getPassword().isEmpty()) {
//            clienteExistente.setPassword(passwordEncoder.encode(cliente.getPassword()));
//        }
//
//        iClienteService.editCliente(clienteExistente);
//        return clienteExistente;
//    }
//
//    @DeleteMapping("/clientes/{id}")
//    public String eliminarCliente(@PathVariable Long id) {
//        iClienteService.deleteCliente(id);
//        return "Cliente eliminado correctamente";
//    }
//
//    // Métodos para gestionar Paquetes
//    @PostMapping("/paquetes/crear")
//    public Paquete crearPaquete(@RequestBody Paquete paquete) {
//        iPaqueteService.savePaquete(paquete);
//        return paquete;
//    }
//
//    @GetMapping("/paquetes/{id}")
//    public Paquete obtenerPaquete(@PathVariable Long id) {
//        return iPaqueteService.findPaquete(id);
//    }
//
//    @PutMapping("/paquetes/{id}")
//    public Paquete actualizarPaquete(@PathVariable Long id, @RequestBody Paquete paquete) {
//        paquete.setId_paquete(id);
//        iPaqueteService.editPaquete(paquete);
//        return paquete;
//    }
//
//    @DeleteMapping("/paquetes/{id}")
//    public String eliminarPaquete(@PathVariable Long id) {
//        iPaqueteService.deletePaquete(id);
//        return "Paquete eliminado correctamente";
//    }
//
//    // Métodos para gestionar Destinos
//    @PostMapping("/destinos/crear")
//    public Destino crearDestino(@RequestBody Destino destino) {
//        iDestinoService.saveDestino(destino);
//        return destino;
//    }
//
//    @GetMapping("/destinos/{id}")
//    public Destino obtenerDestino(@PathVariable Long id) {
//        return iDestinoService.findDestino(id);
//    }
//
//    @PutMapping("/destinos/{id}")
//    public Destino actualizarDestino(@PathVariable Long id, @RequestBody Destino destino) {
//        destino.setId_destino(id);
//        iDestinoService.editDestino(destino);
//        return destino;
//    }
//
//    @DeleteMapping("/destinos/{id}")
//    public String eliminarDestino(@PathVariable Long id) {
//        iDestinoService.deleteDestino(id);
//        return "Destino eliminado correctamente";
//    }
//}