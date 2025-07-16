package com.shds.sma.apps.admin.repository.client;

import com.shds.sma.apps.admin.dto.client.ClientRequestDto;
import com.shds.sma.apps.admin.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientQueryRepository {

    Page<Client> findClientByCond(ClientRequestDto clientRequestDto, Pageable pageable);
}
