package com.daturism.taller3.Service;

import com.daturism.taller3.Model.Destino;
import com.daturism.taller3.Model.Paquete;
import com.daturism.taller3.Repository.IDestinoRepository;
import com.daturism.taller3.Repository.IPaqueteRepository;
import com.daturism.taller3.dto.PaqueteDestinoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaqueteService implements IPaqueteService{

    @Autowired
    private IPaqueteRepository iPaqueteRepository;

    @Autowired
    private IDestinoRepository iDestinoRepository;

    @Override
    public List<Paquete> getPaquetes() {
        List<Paquete> listaPaquetes = iPaqueteRepository.findAll();
        return listaPaquetes;
    }

    @Override
    public void savePaquete(Paquete paquete) {
        iPaqueteRepository.save(paquete);
    }

    @Override
    public void deletePaquete(Long id_paquete) {
        iPaqueteRepository.deleteById(id_paquete);
    }

    @Override
    public Paquete findPaquete(Long id_paquete) {
        Paquete paquete = iPaqueteRepository.findById(id_paquete).orElse(null);
        return paquete;
    }

    @Override
    public void editPaquete(Paquete paquete) {
        this.savePaquete(paquete);
    }


    @Override
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

    @Override
    public PaqueteDestinoDTO destinoByPaquete(Long id_paquete) {
        Optional<Paquete> paqueteOptional = iPaqueteRepository.findById(id_paquete);
        if (paqueteOptional.isPresent()) {
            Paquete paquete = paqueteOptional.get();
            List<Destino> destino = paquete.getListaDeDestinos();
            return new PaqueteDestinoDTO(paquete.getNombre(), destino);
        }
        throw new RuntimeException("Paquete no encontrado");
    }

    @Override
    public Paquete addDestinosInPaquete(Long id_paquete, List<Long> destinoIds) {
        Optional<Paquete> paqueteOptional = iPaqueteRepository.findById(id_paquete);
        if (paqueteOptional.isPresent()) {
            Paquete paquete = paqueteOptional.get();
            for (Long destinoId : destinoIds) {
                Optional<Destino> destinoOptional = iDestinoRepository.findById(destinoId);
                if (destinoOptional.isPresent()) {
                    Destino destino = destinoOptional.get();
                    destino.setPaquete(paquete);
                    iDestinoRepository.save(destino);
                    if (!paquete.getListaDeDestinos().contains(destino)) {
                        paquete.getListaDeDestinos().add(destino);
                    }
                }
            }
            iPaqueteRepository.save(paquete);
            return paquete;
        }
        throw new RuntimeException("Paquete no encontrado");
    }
}

