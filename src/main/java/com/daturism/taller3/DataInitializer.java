package com.daturism.taller3;

import com.daturism.taller3.Model.Destino;
import com.daturism.taller3.Model.Paquete;
import com.daturism.taller3.Repository.IDestinoRepository;
import com.daturism.taller3.Repository.IPaqueteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

//    @Autowired
//    private IDestinoRepository iDestinoRepository;
//
//    @Autowired
//    private IPaqueteRepository iPaqueteRepository;
//
//    @PostConstruct
//    public void initData() {
//        // Crear destinos predeterminados
//        Destino destino1 = new Destino(
//                1L,  // id_destino
//                "Mar del plata",  // nombre
//                "Ciudad con playa",  // descripción
//                100.0,  // precio
//                "Ubicación 1",  // ubicacion
//                "imagen1.jpg",  // imagenUrl
//                null  // paquete
//        );
//
//        Destino destino2 = new Destino(
//                2L,  // id_destino
//                "Destino 2",  // nombre
//                "Descripción de destino 2",  // descripción
//                200.0,  // precio
//                "Ubicación 2",  // ubicacion
//                "imagen2.jpg",  // imagenUrl
//                null  // paquete
//        );
//
//        // Guardar destinos en la base de datos
//        destino1 = iDestinoRepository.save(destino1);
//        destino2 = iDestinoRepository.save(destino2);
//
//        // Crear paquete y asociar los destinos
//        Paquete paquete = new Paquete();
//        paquete.setNombre("Paquete Predeterminado");
//        paquete.setListaDeDestinos(List.of(destino1, destino2)); // Asociar destinos al paquete
//        paquete.setPrecioTotal(destino1.getPrecio() + destino2.getPrecio());
//
//        // Guardar paquete en la base de datos
//        iPaqueteRepository.save(paquete);
//
//        System.out.println("Datos predeterminados inicializados correctamente");
//    }
}