package com.shds.sma.apps.admin.common.service;

import com.shds.sma.apps.admin.member.dto.MemberResponseDto;
import com.shds.sma.apps.system.dto.SystemModRequestDto;
import com.shds.sma.apps.system.dto.SystemRequestDto;
import com.shds.sma.apps.system.dto.SystemResponseDto;
import com.shds.sma.apps.system.dto.SystemSaveRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SystemAdminService {

    List<SystemResponseDto> findSystemAll();

    Page<SystemResponseDto> findSystemByCond(SystemRequestDto systemRequestDto, Pageable pageable);

    SystemResponseDto findSystemById(Long systemId);

    void systemSave(SystemSaveRequestDto systemSaveRequestDto);

    void modifiedSystem(SystemModRequestDto systemModRequestDto);

    void removeSystem(Long systemId);

    void useSystem(Long systemId);

}

