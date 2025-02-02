package com.shds.sma.admin.service;

import com.shds.sma.admin.dto.client.ClientModRequestDto;
import com.shds.sma.admin.dto.client.ClientRequestDto;
import com.shds.sma.admin.dto.client.ClientResponseDto;
import com.shds.sma.admin.dto.client.ClientSaveRequestDto;
import com.shds.sma.admin.dto.notice.*;
import com.shds.sma.admin.dto.system.SystemModRequestDto;
import com.shds.sma.admin.dto.system.SystemRequestDto;
import com.shds.sma.admin.dto.system.SystemResponseDto;
import com.shds.sma.admin.dto.system.SystemSaveRequestDto;
import com.shds.sma.admin.entity.Client;
import com.shds.sma.admin.entity.Notice;
import com.shds.sma.admin.entity.System;
import com.shds.sma.admin.repositroy.client.ClientRepository;
import com.shds.sma.admin.repositroy.notice.NoticeRepository;
import com.shds.sma.admin.repositroy.system.SystemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final NoticeRepository noticeRepository;

    private final SystemRepository systemRepository;

    private final ClientRepository clientRepository;

    private final ModelMapper modelMapper;

    @Override
    public Page<NoticeResponseDto> findNoticeAll(Pageable pageable) {
        Page<Notice> notices = noticeRepository.findAll(pageable);
        return notices.map(NoticeResponseDto::new);
    }

    @Override
    public Page<NoticeResponseDto> findNoticeCond(NoticeCondRequestDto noticeCondRequestDto, Pageable pageable) {
        Page<Notice> notices = noticeRepository.findNoticeCond(noticeCondRequestDto, pageable);
        return notices.map(NoticeResponseDto::new);
    }

    @Override
    public Page<NoticeHomeResponseDto> findHomeNotice(Pageable pageable) {
        Page<Notice> notices = noticeRepository.findHomeNotice(pageable);
        return notices.map(NoticeHomeResponseDto::new);
    }

    @Override
    public NoticeResponseDto findNoticeById(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).get();
        return modelMapper.map(notice, NoticeResponseDto.class);
    }

    @Override
    public void saveNotice(NoticeSaveRequestDto noticeSaveRequestDto) {
        noticeRepository.save(modelMapper.map(noticeSaveRequestDto, Notice.class));
    }

    @Override
    public void modifiedNotice(NoticeModRequestDto noticeModRequestDto) {
        Notice findNotice = noticeRepository.findById(noticeModRequestDto.getNoticeId()).get();
        findNotice.noticeModified(noticeModRequestDto);
    }

    @Override
    public void removeNotice(Long noticeId) {
        Notice findNotice = noticeRepository.findById(noticeId).get();
        findNotice.setValidityN();
    }

    @Override
    public void useNotice(Long noticeId) {
        Notice findNotice = noticeRepository.findById(noticeId).get();
        findNotice.setValidityY();
    }

    @Override
    public Page<SystemResponseDto> findSystemCond(SystemRequestDto systemRequestDto, Pageable pageable) {
        Page<System> systems = systemRepository.findSystemCond(systemRequestDto, pageable);
        return systems.map(SystemResponseDto::new);
    }

    @Override
    public SystemResponseDto findSystemById(Long systemId) {
        System findSystem = systemRepository.findById(systemId).get();
        return modelMapper.map(findSystem, SystemResponseDto.class);
    }

    @Override
    public void systemSave(SystemSaveRequestDto systemSaveRequestDto) {
        systemRepository.save(modelMapper.map(systemSaveRequestDto, System.class));
    }

    @Override
    public void modifiedSystem(SystemModRequestDto systemModRequestDto) {
        System findSystem = systemRepository.findById(systemModRequestDto.getSystemId()).get();
        findSystem.systemModified(systemModRequestDto);
    }

    @Override
    public void removeSystem(Long systemId) {
        System findSystem = systemRepository.findById(systemId).get();
        findSystem.setValidityN();
    }

    @Override
    public void useSystem(Long systemId) {
        System findSystem = systemRepository.findById(systemId).get();
        findSystem.setValidityY();
    }

    @Override
    public Page<ClientResponseDto> findClientCond(ClientRequestDto clientRequestDto, Pageable pageable) {
        Page<Client> clients = clientRepository.findClientCond(clientRequestDto, pageable);
        return clients.map(ClientResponseDto::new);
    }

    @Override
    public ClientResponseDto findClientById(Long clientId) {
        Client findClient = clientRepository.findById(clientId).get();
        return modelMapper.map(findClient, ClientResponseDto.class);
    }

    @Override
    public void clientSave(ClientSaveRequestDto clientSaveRequestDto) {
        clientRepository.save(modelMapper.map(clientSaveRequestDto, Client.class));
    }

    @Override
    public void modifiedClient(ClientModRequestDto clientModRequestDto) {
        Client findClient = clientRepository.findById(clientModRequestDto.getClientId()).get();
        findClient.clientModified(clientModRequestDto);
    }

    @Override
    public void removeClient(Long clientId) {
        log.info("clientId = {}", clientId);
        Client findClient = clientRepository.findById(clientId).get();
        log.info("findClient = {}", findClient.getValidity());
        findClient.setValidityN();
    }

    @Override
    public void useClient(Long clientId) {
        Client findClient = clientRepository.findById(clientId).get();
        findClient.setValidityY();
    }


}
