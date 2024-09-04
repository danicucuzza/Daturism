package com.daturism.taller3.Service;
import com.daturism.taller3.Model.Destino;
import java.util.List;

public interface IDestinoService {
    //OBTENER TODOS LOS DESTINOS
    public List<Destino> getDestinos();

    //GUARDAR UN NUEVO DESTINO
    public void saveDestino (Destino destino);

    //BUSCAR UN DESTINO POR ID
    public Destino findDestino (Long id_destino);

    //ELIMINAR UN DESTINO POR ID
    public void deleteDestino (Long id_destino);

    //EDITAR UN DESTINO EXISTENTE
    public void editDestino (Destino destino);

    //OBTENER DESTINOOS POR LISTA DE IDS
    List<Destino> getDestinosByIds(List<Long> ids);

    //BUSCAR DESTINOS POR PALABRA
    public List<Destino> findDestinoByName(String palabra);
}
