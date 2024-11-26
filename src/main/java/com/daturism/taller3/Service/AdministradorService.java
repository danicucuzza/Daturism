package com.daturism.taller3.Service;

import com.daturism.taller3.Model.Cliente;
import com.daturism.taller3.Model.Destino;
import com.daturism.taller3.Model.Paquete;
import com.daturism.taller3.Repository.IClienteRepository;
import com.daturism.taller3.Repository.IDestinoRepository;
import com.daturism.taller3.Repository.IPaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdministradorService {

    @Autowired
    private IClienteRepository iClienteRepository;

    @Autowired
    private IPaqueteRepository iPaqueteRepository;

    @Autowired
    private IDestinoRepository iDestinoRepository;


    public List<Cliente> getClientes() {
        return iClienteRepository.findAll();
    }


    public void saveCliente(Cliente cliente) {
        iClienteRepository.save(cliente);
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


    public List<Paquete> getPaquetes() {
        return iPaqueteRepository.findAll();
    }

    public void savePaquete(Paquete paquete) {
        iPaqueteRepository.save(paquete);
    }


    public Paquete findPaquete(Long id_paquete) {
        return iPaqueteRepository.findById(id_paquete).orElse(null);
    }


    public void deletePaquete(Long id_paquete) {
        iPaqueteRepository.deleteById(id_paquete);
    }


    public void editPaquete(Paquete paquete) {
        this.savePaquete(paquete);
    }


    public List<Destino> getDestinos() {
        return iDestinoRepository.findAll();
    }


    public void saveDestino(Destino destino) {
        iDestinoRepository.save(destino);
    }


    public Destino findDestino(Long id_destino) {
        return iDestinoRepository.findById(id_destino).orElse(null);
    }

    public void deleteDestino(Long id_destino) {
        iDestinoRepository.deleteById(id_destino);
    }


    public void editDestino(Destino destino) {
        this.saveDestino(destino);
    }


    public List<Destino> getDestinosByIds(List<Long> ids) {
        return iDestinoRepository.findAllById(ids);
    }


    public List<Destino> findDestinoByName(String palabra) {
        String palabraLower = palabra.toLowerCase();
        List<Destino> listaDestinos = iDestinoRepository.findAll();
        List<Destino> listaDestinosFiltrados = new ArrayList<>();

        for (Destino destino : listaDestinos) {
            String textoComparar = destino.getNombre();
            if (textoComparar != null && textoComparar.toLowerCase().contains(palabraLower)) {
                listaDestinosFiltrados.add(destino);
            }
        }
        return listaDestinosFiltrados;
    }
}
