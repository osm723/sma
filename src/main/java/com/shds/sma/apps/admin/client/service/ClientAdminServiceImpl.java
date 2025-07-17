package com.shds.sma.apps.admin.client.service;

import com.shds.sma.apps.admin.client.dto.ClientModRequestDto;
import com.shds.sma.apps.admin.client.dto.ClientRequestDto;
import com.shds.sma.apps.admin.client.dto.ClientResponseDto;
import com.shds.sma.apps.admin.client.dto.ClientSaveRequestDto;
import com.shds.sma.apps.admin.client.entity.Client;
import com.shds.sma.apps.admin.client.repository.ClientRepository;
import com.shds.sma.common.exception.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.shds.sma.common.exception.ExceptionMessageConst.NOT_FOUND_CLIENT;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ClientAdminServiceImpl implements ClientAdminService {

    private final ClientRepository clientRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ClientResponseDto> findClientAll() {
        List<Client> findClient = clientRepository.findAll();
        return findClient.stream().map(ClientResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public Page<ClientResponseDto> findClientByCond(ClientRequestDto clientRequestDto, Pageable pageable) {
        Page<Client> clients = clientRepository.findClientByCond(clientRequestDto, pageable);
        return clients.map(ClientResponseDto::new);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientResponseDto findClientById(Long clientId) {
        Client findClient = getClient(clientId);
        return modelMapper.map(findClient, ClientResponseDto.class);
    }

    @Override
    public void saveClient(ClientSaveRequestDto clientSaveRequestDto) {
        clientRepository.save(modelMapper.map(clientSaveRequestDto, Client.class));
    }

    @Override
    public void modifiedClient(ClientModRequestDto clientModRequestDto) {
        Client findClient = getClient(clientModRequestDto.getClientId());
        findClient.clientModified(clientModRequestDto);
    }

    @Override
    public void removeClient(Long clientId) {
        Client findClient = getClient(clientId);
        findClient.setValidityN();
    }

    @Override
    public void useClient(Long clientId) {
        Client findClient = getClient(clientId);
        findClient.setValidityY();
    }

    private Client getClient(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> new BizException(NOT_FOUND_CLIENT));
    }
}
