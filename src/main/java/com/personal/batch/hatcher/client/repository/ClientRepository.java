package com.personal.batch.hatcher.client.repository;

import com.personal.batch.hatcher.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public interface ClientRepository extends JpaRepository<Client, UUID>
{
    @Modifying
    @Transactional
    @Query("UPDATE CLIENT c set c.lastHeartbeatTimestamp = CURRENT_TIMESTAMP where c.id = :uuid")
    void updateHeartbeatTimestamp(UUID uuid);
}
