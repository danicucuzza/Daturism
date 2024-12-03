package com.daturism.taller3.Service;

import com.daturism.taller3.Model.Cliente;
import com.daturism.taller3.Model.Paquete;
import com.daturism.taller3.Repository.IClienteRepository;
import com.daturism.taller3.Repository.IPaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private IClienteRepository iClienteRepository;

    @Autowired
    private IPaqueteRepository iPaqueteRepository;

    public List<Cliente> getClientes() {
        return iClienteRepository.findAll();
    }

    public Cliente saveCliente(Cliente cliente) {
        iClienteRepository.save(cliente);
        return cliente;
    }

    public Cliente findByEmail(String email) {
        return iClienteRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public Cliente findCliente(Long id_cliente) {
        return iClienteRepository.findById(id_cliente).orElse(null);
    }

    public void deleteCliente(Long id_cliente) {
        iClienteRepository.deleteById(id_cliente);
    }

    public void editCliente(Cliente cliente) {
        this.saveCliente(cliente);
    }

    public List<Cliente> getClientesByIds(List<Long> ids) {
        return iClienteRepository.findAllById(ids);
    }

    public List<Cliente> findClienteByName(String nombre) {
        // Implementar lógica de búsqueda por nombre si es necesario
        return null;
    }

    public void comprarPaquete(Long clienteId, Paquete paquete) {
    }
}
