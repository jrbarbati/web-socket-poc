package com.personal.batch.hatcher.client.service;

import com.personal.batch.hatcher.client.model.Client;
import com.personal.batch.hatcher.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<Client> findById(UUID clientId)
    {
        return clientRepository.findById(clientId);
    }

    public void updateLastHeartbeatTimestampToCurrentTime(UUID clientId)
    {
        updateLastHeartbeatTimestamp(clientId, currentTime());
    }

    public void updateLastHeartbeatTimestamp(UUID clientId, Timestamp lastHeartbeatTimestamp)
    {
        clientRepository.updateLastHeartbeatTimestamp(clientId, lastHeartbeatTimestamp);
    }

    public Client save(Client client)
    {
        return clientRepository.save(client);
    }

    protected Timestamp currentTime()
    {
        return new Timestamp(System.currentTimeMillis());
    }
}
