package com.daturism.taller3.Service;

import com.daturism.taller3.Model.Cliente;
import com.daturism.taller3.Model.Paquete;
import com.daturism.taller3.Repository.IClienteRepository;
import com.daturism.taller3.Repository.IPaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository iClienteRepository;

    @Autowired
    private IPaqueteRepository iPaqueteRepository;

    @Override
    public List<Cliente> getClientes() {
        return iClienteRepository.findAll();
    }

    @Override
    public void saveCliente(Cliente cliente) {
        iClienteRepository.save(cliente);
    }

    @Override
    public Cliente findCliente(Long id_cliente) {
        return iClienteRepository.findById(id_cliente).orElse(null);
    }

    @Override
    public void deleteCliente(Long id_cliente) {
        iClienteRepository.deleteById(id_cliente);
    }

    @Override
    public void editCliente(Cliente cliente) {
        this.saveCliente(cliente);
    }

    @Override
    public List<Cliente> getClientesByIds(List<Long> ids) {
        return iClienteRepository.findAllById(ids);
    }

    @Override
    public List<Cliente> findClienteByName(String nombre) {
        // Implementar lógica de búsqueda por nombre si es necesario
        return null;
    }

    @Override
    public Cliente addPaqueteInCliente(Long id_cliente, List<Long> paquetesIds) {
        Optional<Cliente> clienteOptional = iClienteRepository.findById(id_cliente);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            for (Long paqueteId : paquetesIds) {
                Optional<Paquete> paqueteOptional = iPaqueteRepository.findById(paqueteId);
                if (paqueteOptional.isPresent()) {
                    Paquete paquete = paqueteOptional.get();
                    paquete.setCliente(cliente);
                    iPaqueteRepository.save(paquete);
                    if (!cliente.getListaDePaquetes().contains(paquete)) {
                        cliente.getListaDePaquetes().add(paquete);
                    }
                }
            }
            iClienteRepository.save(cliente);
            return cliente;
        }
        throw new RuntimeException("Paquete no encontrado");
    }

    @Override
    public void comprarPaquete(Long clienteId, Paquete paquete) {

    }

//    @Override
//    public void comprarPaquete(Long clienteId, Paquete paquete) {
//        agregarPaquete(clienteId, paquete);
//        // Implementar lógica adicional para manejar la compra
//    }
}
