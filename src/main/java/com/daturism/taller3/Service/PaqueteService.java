package com.daturism.taller3.Service;

import com.daturism.taller3.Model.Paquete;
import com.daturism.taller3.Repository.PaqueteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaqueteService {
    private final PaqueteRepository paqueteRepository;

    public PaqueteService(PaqueteRepository paqueteRepository) {
        this.paqueteRepository = paqueteRepository;
    }

    public Paquete crearPaquete(Paquete paquete) {
        return paqueteRepository.save(paquete);
    }

    public Optional<Paquete> obtenerPaquete(Long id) {
        return paqueteRepository.findById(id);
    }

    public List<Paquete> obtenerTodosLosPaquetes() {
        return paqueteRepository.findAll();
    }

    public void eliminarPaquete(Long id) {
        paqueteRepository.deleteById(id);
    }
}
