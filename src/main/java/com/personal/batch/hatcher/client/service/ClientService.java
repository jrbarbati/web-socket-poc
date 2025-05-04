package com.personal.batch.hatcher.client.service;

import com.personal.batch.hatcher.client.model.Client;
import com.personal.batch.hatcher.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    public Client update(Client client)
    {
        return save(client);
    }

    public Client create(Client client)
    {
        return save(client);
    }

    public void updateLastHeartbeatTimestampToCurrentTime(Client client)
    {
        updateLastHeartbeatTimestamp(client, currentTime());
    }

    public void updateLastHeartbeatTimestamp(Client client, Timestamp lastHeartbeatTimestamp)
    {
        clientRepository.updateLastHeartbeatTimestamp(client.getId(), lastHeartbeatTimestamp);
    }

    protected Client save(Client client)
    {
        return clientRepository.save(client);
    }

    protected Timestamp currentTime()
    {
        return new Timestamp(System.currentTimeMillis());
    }
}
