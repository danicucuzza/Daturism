package com.daturism.taller3.Service;
import com.daturism.taller3.Model.Paquete;
import com.daturism.taller3.dto.PaqueteDestinoDTO;

import java.util.List;

public interface IPaqueteService {

    //OBTENER TODOS LOS PAQUETES
    public List<Paquete> getPaquetes();

    //GUARDAR UN NUEVO PAQUETE
    public void savePaquete (Paquete paquete);

    //ELIMINAR UN PAQUETE POR ID
    public void deletePaquete (Long id_paquete);

    //BUSCAR UN PAQUETE POR ID
    public Paquete findPaquete (Long id_paquete);

    //EDITAR UN PAQUETE EXISTENTE
    public void editPaquete(Paquete paquete);

    //BUSCAR PAQUETE POR NOMBRE (PALABRA CLAVE)
    public List<Paquete> findPaqueteByName (String palabra);

    //OBTENER DESTINO DE UN PAQUETE POR ID
    public PaqueteDestinoDTO destinoByPaquete (Long id_paquete);

    //AGREGAR DESTINOS A UN PAQUETE EXISTENTE
    public Paquete addDestinosInPaquete (Long id_paquete, List<Long> destinoIds);

    void saveAll(List<Paquete> paquetes);
}
