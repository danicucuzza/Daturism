package com.daturism.taller3.Service;

import com.daturism.taller3.Model.Destino;
import com.daturism.taller3.Model.Paquete;
import com.daturism.taller3.Repository.DestinoRepository;
import com.daturism.taller3.Repository.PaqueteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaqueteService {
    private final PaqueteRepository paqueteRepository;
    private final DestinoRepository destinoRepository;

    public PaqueteService(PaqueteRepository paqueteRepository, DestinoRepository destinoRepository) {
        this.paqueteRepository = paqueteRepository;
        this.destinoRepository = destinoRepository;
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

    public Paquete asignarDestino(Long paqueteId, Long destinoId) {
        Optional<Paquete> paqueteOpt = paqueteRepository.findById(paqueteId);
        Optional<Destino> destinoOpt = destinoRepository.findById(destinoId);

        if (paqueteOpt.isPresent() && destinoOpt.isPresent()) {
            Paquete paquete = paqueteOpt.get();
            Destino destino = destinoOpt.get();
            paquete.setDestino(destino);
            return paqueteRepository.save(paquete);
        } else {
        throw new RuntimeException("Paquete o Destino no encontrado");
        }
    }
}
