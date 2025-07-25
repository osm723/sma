package com.shds.sma.apps.ip.service;

import com.shds.sma.apps.ip.dto.IpRequestDto;
import com.shds.sma.apps.ip.dto.IpResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IpService {
    Page<IpResponseDto> findIpByCond(IpRequestDto ipRequestDto, Pageable pageable);

    IpResponseDto findIpById(Long ipId);

    void getIpPreExpiration();

    void getIpPreExpirationToManager();
}
