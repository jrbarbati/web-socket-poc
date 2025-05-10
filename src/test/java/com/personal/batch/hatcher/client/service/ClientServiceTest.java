package com.personal.batch.hatcher.client.service;

import com.personal.batch.hatcher.client.model.Client;
import com.personal.batch.hatcher.client.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest
{
    private ClientService clientService;

    @Mock
    private ClientRepository mockClientRepository;

    @BeforeEach
    void setup()
    {
        clientService = spy(new ClientService(mockClientRepository));
    }

    @Test
    void findAll()
    {
        when(mockClientRepository.findAll()).thenReturn(List.of());

        List<Client> clients = clientService.findAll();

        assertNotNull(clients);

        verify(mockClientRepository, times(1)).findAll();
    }

    @Test
    void findByInstanceId()
    {
        UUID instanceId = UUID.randomUUID();

        when(mockClientRepository.findByInstanceId(instanceId)).thenReturn(Optional.of(new Client()));

        Optional<Client> client = clientService.findByInstanceId(instanceId);

        assertTrue(client.isPresent());

        verify(mockClientRepository, times(1)).findByInstanceId(instanceId);
    }

    @Test
    void updateLastHeartbeatTimestampToCurrentTime()
    {
        UUID instanceId = UUID.randomUUID();
        Timestamp lastHearbeatTimestamp = new Timestamp(System.currentTimeMillis());

        doReturn(lastHearbeatTimestamp).when(clientService).currentTime();
        doReturn(new Client()).when(clientService).updateLastHeartbeatTimestamp(instanceId, lastHearbeatTimestamp);

        Client client = clientService.updateLastHeartbeatTimestampToCurrentTime(instanceId);

        assertNotNull(client);

        verify(clientService, times(1)).currentTime();
        verify(clientService, times(1)).updateLastHeartbeatTimestamp(instanceId, lastHearbeatTimestamp);
    }

    @Test
    void updateLastHeartbeatTimestamp_existing()
    {
        UUID instanceId = UUID.randomUUID();

        Client client = new Client();
        client.setInstanceId(instanceId);
        client.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        client.setLastHeartbeatTimestamp(new Timestamp(System.currentTimeMillis()));

        doReturn(Optional.of(client)).when(clientService).findByInstanceId(instanceId);
        doReturn(client).when(clientService).save(client);

        Client updated = clientService.updateLastHeartbeatTimestampToCurrentTime(instanceId);

        assertNotNull(updated);

        verify(clientService, times(1)).findByInstanceId(instanceId);
        verify(clientService, times(1)).save(argThat(arg -> arg.getCreatedAt() != null));
    }

    @Test
    void updateLastHeartbeatTimestamp_new()
    {
        UUID instanceId = UUID.randomUUID();

        doReturn(Optional.empty()).when(clientService).findByInstanceId(instanceId);
        doReturn(new Client()).when(clientService).save(any(Client.class));

        Client client = clientService.updateLastHeartbeatTimestampToCurrentTime(instanceId);

        assertNotNull(client);

        verify(clientService, times(1)).findByInstanceId(instanceId);
        verify(clientService, times(1)).save(argThat(arg -> arg.getCreatedAt() == null));
    }

    @Test
    void save()
    {
        UUID instanceId = UUID.randomUUID();

        Client client = new Client();
        client.setInstanceId(instanceId);

        when(mockClientRepository.save(client)).thenReturn(client);

        Client saved = clientService.save(client);

        assertNotNull(saved);

        verify(mockClientRepository, times(1)).save(client);
    }
}