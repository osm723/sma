package com.shds.sma.apps.admin.client.repository;

import com.shds.sma.apps.admin.client.dto.ClientRequestDto;
import com.shds.sma.apps.admin.client.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientQueryRepository {

    Page<Client> findClientByCond(ClientRequestDto clientRequestDto, Pageable pageable);
}
