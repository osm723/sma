package com.shds.sma.admin.repositroy.client;

import com.shds.sma.admin.dto.client.ClientRequestDto;
import com.shds.sma.admin.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientQueryRepository {

    Page<Client> findClientByCond(ClientRequestDto clientRequestDto, Pageable pageable);
}
