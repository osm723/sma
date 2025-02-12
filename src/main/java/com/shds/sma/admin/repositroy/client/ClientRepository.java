package com.shds.sma.admin.repositroy.client;

import com.shds.sma.admin.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>, ClientQueryRepository {
    Client findByClientCode(String clientCode);
}
