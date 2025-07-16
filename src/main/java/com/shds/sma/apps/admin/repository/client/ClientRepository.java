package com.shds.sma.apps.admin.repository.client;

import com.shds.sma.apps.admin.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>, ClientQueryRepository {
    Client findByClientCode(String clientCode);
}
