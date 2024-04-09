package com.danic.Taller3.service;

import com.danic.Taller3.model.Client;
import com.danic.Taller3.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Client createClient(Client client) {
        clientRepository.save(client);
        return client;
    }

    public Client deleteClient(long id) {
        Client client = clientRepository.findById(id).orElseThrow(()->(new RuntimeException("Invalid Client")));
        clientRepository.deleteById(id);
        return client;
    }

    public Client findClientById(long id){
        return null;
    }

    public Client updateClientById(long id, Client client){
        return null;
    }
}
