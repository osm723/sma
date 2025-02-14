package com.shds.sma.cert.repository;

import com.shds.sma.cert.dto.CertRequestDto;
import com.shds.sma.cert.entity.Cert;
import com.shds.sma.system.dto.SystemCertRequestDto;
import com.shds.sma.system.dto.SystemCertResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CertQueryRepository  {
    Page<Cert> findCertByCond(CertRequestDto certRequestDto, Pageable pageable);

    Page<SystemCertResponseDto> findSystemCertByCond(SystemCertRequestDto systemCertRequestDto, Pageable pageable);

    List<Cert> findCertPreExpiration();

    boolean isCertReApply(Cert cert);
}
