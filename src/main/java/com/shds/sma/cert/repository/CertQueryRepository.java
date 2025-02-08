package com.shds.sma.cert.repository;

import com.shds.sma.cert.dto.CertRequestDto;
import com.shds.sma.cert.entity.Cert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CertQueryRepository  {
    Page<Cert> findCertByCond(CertRequestDto certRequestDto, Pageable pageable);
}
