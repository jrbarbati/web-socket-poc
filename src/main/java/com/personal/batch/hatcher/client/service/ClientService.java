package com.personal.batch.hatcher.client.service;

import com.personal.batch.hatcher.client.model.Client;
import com.personal.batch.hatcher.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService
{
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository)
    {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll()
    {
        return clientRepository.findAll();
    }
}
