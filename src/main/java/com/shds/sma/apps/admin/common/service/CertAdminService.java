package com.shds.sma.apps.admin.common.service;

import com.shds.sma.apps.cert.dto.CertModRequestDto;
import com.shds.sma.apps.cert.dto.CertRequestDto;
import com.shds.sma.apps.cert.dto.CertResponseDto;
import com.shds.sma.apps.cert.dto.CertSaveRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CertAdminService {

    Page<CertResponseDto> findCertByCond(CertRequestDto certRequestDto, Pageable pageable);

    CertResponseDto findCertById(Long certId);

    void saveCert(CertSaveRequestDto certSaveRequestDto);

    void modifiedCert(CertModRequestDto certModRequestDto);

    void removeCert(Long certId);

    void useCert(Long certId);
}
