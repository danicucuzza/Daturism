package com.daturism.taller3.Service;

import com.daturism.taller3.Model.Cliente;
import com.daturism.taller3.Repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarritoService {

    @Autowired
    private IClienteRepository iClienteRepository;

    @Transactional
    public void comprarCarrito(Long id) {
        // Lógica para procesar la compra del carrito, probablemente buscar el cliente por ID y actualizar los registros necesarios.
        Cliente cliente = iClienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        // lógica de negocio para procesar la compra
    }
}
