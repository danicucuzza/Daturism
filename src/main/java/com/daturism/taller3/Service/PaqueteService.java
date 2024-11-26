package com.daturism.taller3.Service;

import com.daturism.taller3.Model.Destino;
import com.daturism.taller3.Model.Paquete;
import com.daturism.taller3.Repository.IDestinoRepository;
import com.daturism.taller3.Repository.IPaqueteRepository;
import com.daturism.taller3.dto.PaqueteDestinoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaqueteService{

    @Autowired
    private IPaqueteRepository iPaqueteRepository;

    @Autowired
    private IDestinoRepository iDestinoRepository;


    public List<Paquete> getPaquetes() {
        List<Paquete> listaPaquetes = iPaqueteRepository.findAll();
        return listaPaquetes;
    }

    public void savePaquete(Paquete paquete) {
        iPaqueteRepository.save(paquete);
    }

    public void deletePaquete(Long id_paquete) {
        iPaqueteRepository.deleteById(id_paquete);
    }

    public Paquete findPaquete(Long id_paquete) {
        Paquete paquete = iPaqueteRepository.findById(id_paquete).orElse(null);
        return paquete;
    }

    public void editPaquete(Paquete paquete) {
        this.savePaquete(paquete);
    }

    public List<Paquete> findPaqueteByName(String palabra) {
        String palabraLower = palabra.toLowerCase();
        List<Paquete> listaPaquetes = iPaqueteRepository.findAll();
        List<Paquete> listaPaquetesFiltrado = new ArrayList<>();

        for (Paquete paquete : listaPaquetes) {
            String textoComparar = paquete.getNombre();
            if (textoComparar != null && textoComparar.toLowerCase().contains(palabraLower)) {
                listaPaquetesFiltrado.add(paquete);
            }
        }
        return listaPaquetesFiltrado;
    }

    public PaqueteDestinoDTO destinoByPaquete(Long id_paquete) {
        Optional<Paquete> paqueteOptional = iPaqueteRepository.findById(id_paquete);
        if (paqueteOptional.isPresent()) {
            Paquete paquete = paqueteOptional.get();
            List<Destino> destino = paquete.getListaDeDestinos();
            return new PaqueteDestinoDTO(paquete.getNombre(), destino);
        }
        throw new RuntimeException("Paquete no encontrado");
    }

    @Transactional
    public Paquete addDestinosInPaquete(Long idPaquete, List<Long> destinoIds) {
        // Obtener el paquete por ID
        Paquete paquete = iPaqueteRepository.findById(idPaquete)
                .orElseThrow(() -> new RuntimeException("Paquete no encontrado"));

        // Obtener los destinos por sus IDs
        List<Destino> destinos = iDestinoRepository.findAllById(destinoIds);

        // Crear una nueva lista modificable para los destinos
        List<Destino> listaDeDestinos = new ArrayList<>(paquete.getListaDeDestinos());
        listaDeDestinos.addAll(destinos);

        // Asociar los destinos al paquete
        paquete.setListaDeDestinos(listaDeDestinos);

        // Sumar los precios de todos los destinos asociados al paquete
        BigDecimal totalPrecio = listaDeDestinos.stream()
                .map(Destino::getPrecio)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Actualizar el precio total del paquete
        paquete.setPrecioTotal(totalPrecio);

        // Guardar el paquete actualizado en la base de datos
        return iPaqueteRepository.save(paquete);
    }

    public void saveAll(List<Paquete> paquetes) {
        iPaqueteRepository.saveAll(paquetes);
    }
}

