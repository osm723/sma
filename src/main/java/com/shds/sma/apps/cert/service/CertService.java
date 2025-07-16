package com.shds.sma.apps.cert.service;

import com.shds.sma.apps.cert.dto.CertRequestDto;
import com.shds.sma.apps.cert.dto.CertResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CertService {

    Page<CertResponseDto> findCertByCond(CertRequestDto certRequestDto, Pageable pageable);

    CertResponseDto findCertById(Long certId);

    void getCertPreExpiration();

    void getCertPreExpirationToManager();
}
