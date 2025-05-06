package com.personal.batch.hatcher.client.repository;

import com.personal.batch.hatcher.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.UUID;

@Component
public interface ClientRepository extends JpaRepository<Client, UUID>
{
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CLIENT (ID, CREATED_AT, LAST_UPDATED_TIMESTAMP) " +
            "VALUES (:clientId, :lastHeartbeatTimestamp, :lastHeartbeatTimestamp) " +
            "ON DUPLICATE KEY UPDATE " +
            "    LAST_UPDATED_TIMESTAMP = :lastHeartbeatTimestamp", nativeQuery = true)
    void updateLastHeartbeatTimestamp(UUID clientId, Timestamp lastHeartbeatTimestamp);
}
