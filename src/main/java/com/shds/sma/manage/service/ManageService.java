package com.shds.sma.manage.service;

import com.shds.sma.manage.dto.cert.CertRequestDto;
import com.shds.sma.manage.dto.cert.CertResponseDto;
import com.shds.sma.manage.dto.ip.IpRequestDto;
import com.shds.sma.manage.dto.ip.IpResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManageService {
    Page<IpResponseDto> findIpByCond(IpRequestDto ipRequestDto, Pageable pageable);

    Page<CertResponseDto> findCertByCond(CertRequestDto certRequestDto, Pageable pageable);
}
