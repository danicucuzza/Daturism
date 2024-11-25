package com.daturism.taller3.Service;
import com.daturism.taller3.Model.Destino;
import com.daturism.taller3.Repository.IDestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DestinoService implements IDestinoService{

    @Autowired
    private IDestinoRepository iDestinoRepository;

    @Override
    public List<Destino> getDestinos() {
        List<Destino> listaDestinos = iDestinoRepository.findAll();
        return listaDestinos;
    }

    @Override
    public void saveDestino(Destino destino) {
        iDestinoRepository.save(destino);
    }

    @Override
    public Destino findDestino(Long id_destino) {
        Destino destino = iDestinoRepository.findById(id_destino).orElse(null);
        return destino;
    }

    @Override
    public void deleteDestino(Long id_destino) {
        iDestinoRepository.deleteById(id_destino);
    }

    @Override
    public void editDestino(Destino destino) {
        this.saveDestino(destino);
    }

    @Override
    public List<Destino> getDestinosByIds(List<Long> ids) {
        return iDestinoRepository.findAllById(ids);
    }

    @Override
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

    @Override
    public void saveAll(List<Destino> destinos) {
        iDestinoRepository.saveAll(destinos);
    }
}
