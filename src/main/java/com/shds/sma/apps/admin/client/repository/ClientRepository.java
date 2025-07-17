package com.shds.sma.apps.admin.client.repository;

import com.shds.sma.apps.admin.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>, ClientQueryRepository {
    Client findByClientCode(String clientCode);
}
