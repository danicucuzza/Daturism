package com.daturism.taller3;

import com.daturism.taller3.Model.Cliente;
import com.daturism.taller3.Model.Destino;
import com.daturism.taller3.Model.Paquete;
import com.daturism.taller3.Service.ClienteService;
import com.daturism.taller3.Service.DestinoService;
import com.daturism.taller3.Service.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private DestinoService destinoService;

    @Autowired
    private PaqueteService paqueteService;

    @Autowired
    private ClienteService clienteService;


    @Transactional
    @Override
    public void run(String... args) throws Exception {
        // Crear Destinos sin asignarles un paquete
        Destino destino1 = new Destino("Buenos Aires", "La capital de Argentina, conocida por su rica vida cultural y su arquitectura europea.", new BigDecimal("1000.00"), "Buenos Aires", "http://example.com/buenos_aires.jpg");
        Destino destino2 = new Destino("Bariloche", "Destino popular por sus lagos y montañas, ideal para esquiar en invierno.", new BigDecimal("1500.00"), "Bariloche", "http://example.com/bariloche.jpg");
        Destino destino3 = new Destino("Mendoza", "Famosa por sus bodegas y la producción de vino Malbec.", new BigDecimal("1200.00"), "Mendoza", "http://example.com/mendoza.jpg");
        Destino destino4 = new Destino("Salta", "Conocida por su arquitectura colonial y paisajes montañosos.", new BigDecimal("1300.00"), "Salta", "http://example.com/salta.jpg");
        Destino destino5 = new Destino("Córdoba", "Ciudad universitaria con una vibrante vida nocturna y bellos edificios coloniales.", new BigDecimal("1100.00"), "Córdoba", "http://example.com/cordoba.jpg");
        Destino destino6 = new Destino("Ushuaia", "La ciudad más austral del mundo, puerta de entrada a la Antártida.", new BigDecimal("2000.00"), "Ushuaia", "http://example.com/ushuaia.jpg");
        destinoService.saveAll(Arrays.asList(destino1, destino2, destino3, destino4, destino5, destino6));

        // Crear Paquetes con destinos asignados
        Paquete paquete1 = new Paquete("Paquete Andes", "Explora la majestuosidad de los Andes con este paquete que incluye los destinos más icónicos.", "3 days", "http://example.com/paquete_andes.jpg");
        Paquete paquete2 = new Paquete("Paquete Patagónico", "Sumérgete en la belleza de la Patagonia con este exclusivo paquete.", "7 days", "http://example.com/paquete_patagonico.jpg");
        paqueteService.saveAll(Arrays.asList(paquete1, paquete2));

        Cliente cliente1 = new Cliente("Juan", "juan.perez@gmail.com", "password123", "1544236543", "calle falsa 123");
        clienteService.saveCliente(cliente1);
    }
}