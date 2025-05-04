package com.personal.batch.hatcher.client.repository;

import com.personal.batch.hatcher.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface ClientRepository extends JpaRepository<Client, UUID>
{}
