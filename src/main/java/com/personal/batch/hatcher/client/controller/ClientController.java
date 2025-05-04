package com.personal.batch.hatcher.client.controller;

import com.personal.batch.hatcher.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class ClientController
{
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService)
    {
        this.clientService = clientService;
    }

    @GetMapping(value = "/clients", produces = "application/json")
    public ResponseEntity<Object> fetchClients()
    {
        return ResponseEntity.notFound().build();
    }
}
