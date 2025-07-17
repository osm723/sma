package com.shds.sma.apps.admin.common.service;

import com.shds.sma.apps.ip.dto.IpModRequestDto;
import com.shds.sma.apps.ip.dto.IpRequestDto;
import com.shds.sma.apps.ip.dto.IpResponseDto;
import com.shds.sma.apps.ip.dto.IpSaveRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IpAdminService {

    Page<IpResponseDto> findIpByCond(IpRequestDto ipRequestDto, Pageable pageable);

    IpResponseDto findIpById(Long ipId);

    void saveIp(IpSaveRequestDto ipSaveRequestDto);

    void modifiedIp(IpModRequestDto ipModRequestDto);

    void removeIp(Long ipId);

    void useIp(Long ipId);
}
