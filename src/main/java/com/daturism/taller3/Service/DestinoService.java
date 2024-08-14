package com.daturism.taller3.Service;

import com.daturism.taller3.Model.Destino;
import com.daturism.taller3.Repository.DestinoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinoService {
    private final DestinoRepository destinoRepository;

    public DestinoService(DestinoRepository destinoRepository) {
        this.destinoRepository = destinoRepository;
    }

    public Destino crearDestino(Destino destino) {
        return destinoRepository.save(destino);
    }

    public Optional<Destino> obtenerDestino(Long id) {
        return destinoRepository.findById(id);
    }

    public List<Destino> obtenerTodosLosDestinos() {
        return destinoRepository.findAll();
    }

    public void eliminarDestino(Long id) {
        destinoRepository.deleteById(id);
    }
}
