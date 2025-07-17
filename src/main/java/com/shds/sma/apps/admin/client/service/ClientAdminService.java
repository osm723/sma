package com.shds.sma.apps.admin.client.service;

import com.shds.sma.apps.admin.client.dto.ClientModRequestDto;
import com.shds.sma.apps.admin.client.dto.ClientRequestDto;
import com.shds.sma.apps.admin.client.dto.ClientResponseDto;
import com.shds.sma.apps.admin.client.dto.ClientSaveRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientAdminService {

    List<ClientResponseDto> findClientAll();

    Page<ClientResponseDto> findClientByCond(ClientRequestDto clientRequestDto, Pageable pageable);

    ClientResponseDto findClientById(Long clientId);

    void saveClient(ClientSaveRequestDto clientSaveRequestDto);

    void modifiedClient(ClientModRequestDto clientModRequestDto);

    void removeClient(Long clientId);

    void useClient(Long clientId);
}
