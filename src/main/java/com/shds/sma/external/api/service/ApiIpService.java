package com.shds.sma.external.api.service;

import com.shds.sma.external.api.dto.ip.ApiIpModRequestDto;
import com.shds.sma.external.api.dto.ip.ApiIpResponseDto;
import com.shds.sma.external.api.dto.ip.ApiIpSaveRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApiIpService {
    Page<ApiIpResponseDto> getAllIps(Pageable pageable);

    ApiIpResponseDto getIp(Long ipId);

    ApiIpResponseDto createIp(ApiIpSaveRequestDto apiIpSaveRequestDto);

    ApiIpResponseDto updateIp(ApiIpModRequestDto apiIpModRequestDto);

    void deleteIp(Long ipId);
}
