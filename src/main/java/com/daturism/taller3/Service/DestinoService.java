package com.daturism.taller3.Service;
import com.daturism.taller3.Model.Destino;
import com.daturism.taller3.Repository.IDestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DestinoService{

    @Autowired
    private IDestinoRepository iDestinoRepository;

    public List<Destino> getDestinos() {
        List<Destino> listaDestinos = iDestinoRepository.findAll();
        return listaDestinos;
    }

    public void saveDestino(Destino destino) {
        iDestinoRepository.save(destino);
    }

    public Destino findDestino(Long id_destino) {
        Destino destino = iDestinoRepository.findById(id_destino).orElse(null);
        return destino;
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

    public void saveAll(List<Destino> destinos) {
        iDestinoRepository.saveAll(destinos);
    }
}
