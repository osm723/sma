package com.shds.sma.apps.cert.service;

import com.shds.sma.apps.admin.dto.member.MemberResponseDto;
import com.shds.sma.apps.system.dto.SystemResponseDto;
import com.shds.sma.apps.cert.dto.CertRequestDto;
import com.shds.sma.apps.cert.dto.CertResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CertService {

    Page<CertResponseDto> findCertByCond(CertRequestDto certRequestDto, Pageable pageable);

    CertResponseDto findCertById(Long certId);

    List<MemberResponseDto> findMemberAll();

    List<SystemResponseDto> findSystemAll();

    void getCertPreExpiration();

    void getCertPreExpirationToManager();
}
