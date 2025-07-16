package com.shds.sma.apps.system.service;

import com.shds.sma.apps.system.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SystemService {
    List<SystemResponseDto> findSystemAll();

    Page<SystemIpResponseDto> findSystemIpByCond(SystemIpRequestDto systemIpRequestDto, Pageable pageable);

    Page<SystemCertResponseDto> findSystemCertByCond(SystemCertRequestDto systemCertRequestDto, Pageable pageable);

    Page<SystemManagerResponseDto> findSystemMemberByCond(SystemManagerRequestDto systemManagerRequestDto, Pageable pageable);
}
