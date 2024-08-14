package com.daturism.taller3.Service;

import com.daturism.taller3.Model.Reserva;
import com.daturism.taller3.Repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Reserva crearReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Optional<Reserva> obtenerReserva(Long id) {
        return reservaRepository.findById(id);
    }

    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepository.findAll();
    }

    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}
