package com.shds.sma.manage.repository.cert;

import com.shds.sma.manage.dto.cert.CertRequestDto;
import com.shds.sma.manage.entity.Cert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CertQueryRepository  {
    Page<Cert> findCertByCond(CertRequestDto certRequestDto, Pageable pageable);
}
