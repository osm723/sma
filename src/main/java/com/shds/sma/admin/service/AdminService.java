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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {

    Page<NoticeResponseDto> findNoticeAll(Pageable pageable);

    Page<NoticeResponseDto> findNoticeCond(NoticeCondRequestDto noticeCondRequestDto, Pageable pageable);

    Page<NoticeHomeResponseDto> findHomeNotice(Pageable pageable);

    NoticeResponseDto findNoticeById(Long noticeId);

    void saveNotice(NoticeSaveRequestDto noticeSaveRequestDto);

    void modifiedNotice(NoticeModRequestDto noticeModRequestDto);

    void removeNotice(Long noticeId);

    void useNotice(Long noticeId);

    Page<SystemResponseDto> findSystemCond(SystemRequestDto systemRequestDto, Pageable pageable);

    SystemResponseDto findSystemById(Long systemId);

    void systemSave(SystemSaveRequestDto systemSaveRequestDto);

    void modifiedSystem(SystemModRequestDto systemModRequestDto);

    void removeSystem(Long systemId);

    void useSystem(Long systemId);

    Page<ClientResponseDto> findClientCond(ClientRequestDto clientRequestDto, Pageable pageable);

    ClientResponseDto findClientById(Long clientId);

    void clientSave(ClientSaveRequestDto clientSaveRequestDto);

    void modifiedClient(ClientModRequestDto clientModRequestDto);

    void removeClient(Long clientId);

    void useClient(Long clientId);


}
