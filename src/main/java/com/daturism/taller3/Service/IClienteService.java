package com.daturism.taller3.Service;

import com.daturism.taller3.Model.Cliente;
import com.daturism.taller3.Model.Paquete;

import java.util.List;

public interface IClienteService {

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

    // OBTENER CLIENTES POR LISTA DE IDS
    List<Cliente> getClientesByIds(List<Long> ids);

    // BUSCAR CLIENTES POR NOMBRE
    List<Cliente> findClienteByName(String nombre);

    //AGREGAR PRODUCTOS AL CARRITO
    void addToCart(Long clienteId, Paquete paquete);

    //QUITAR PRODUCTOS DEL CARRITO
    void removeFromCart(Long clienteId, Paquete paquete);

//    // AGREGAR UN PAQUETE A UN CLIENTE
//    public Cliente addPaqueteInCliente(Long id_cliente, List<Long> paquetesIds);

    // COMPRAR UN PAQUETE
    void comprarPaquete(Long clienteId, Paquete paquete);
}
