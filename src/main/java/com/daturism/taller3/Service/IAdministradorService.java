package com.daturism.taller3.Service;

import com.daturism.taller3.Model.Cliente;
import com.daturism.taller3.Model.Destino;
import com.daturism.taller3.Model.Paquete;

import java.util.List;

public interface IAdministradorService {

    // CLIENTES
    // OBTENER TODOS LOS CLIENTES
    List<Cliente> getClientes();

    // GUARDAR UN NUEVO CLIENTE
    void saveCliente(Cliente cliente);

    // BUSCAR UN CLIENTE POR ID
    Cliente findCliente(Long id_cliente);

    // ELIMINAR UN CLIENTE POR ID
    void deleteCliente(Long id_cliente);

    // EDITAR UN CLIENTE EXISTENTE
    void editCliente(Cliente cliente);

    // PAQUETES
    // OBTENER TODOS LOS PAQUETES
    List<Paquete> getPaquetes();

    // GUARDAR UN NUEVO PAQUETE
    void savePaquete(Paquete paquete);

    // BUSCAR UN PAQUETE POR ID
    Paquete findPaquete(Long id_paquete);

    // ELIMINAR UN PAQUETE POR ID
    void deletePaquete(Long id_paquete);

    // EDITAR UN PAQUETE EXISTENTE
    void editPaquete(Paquete paquete);

    // DESTINOS
    // OBTENER TODOS LOS DESTINOS
    List<Destino> getDestinos();

    // GUARDAR UN NUEVO DESTINO
    void saveDestino(Destino destino);

    // BUSCAR UN DESTINO POR ID
    Destino findDestino(Long id_destino);

    // ELIMINAR UN DESTINO POR ID
    void deleteDestino(Long id_destino);

    // EDITAR UN DESTINO EXISTENTE
    void editDestino(Destino destino);

    // OBTENER DESTINOS POR LISTA DE IDS
    List<Destino> getDestinosByIds(List<Long> ids);

    // BUSCAR DESTINOS POR PALABRA
    List<Destino> findDestinoByName(String palabra);
}
