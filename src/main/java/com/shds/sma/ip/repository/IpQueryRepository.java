package com.shds.sma.ip.repository;

import com.shds.sma.ip.dto.IpDistinctResponseDto;
import com.shds.sma.ip.dto.IpRequestDto;
import com.shds.sma.ip.entity.Ip;
import com.shds.sma.system.dto.SystemIpRequestDto;
import com.shds.sma.system.dto.SystemIpResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IpQueryRepository {
    Page<Ip> findIpByCond(IpRequestDto ipRequestDto, Pageable pageable);

    Page<SystemIpResponseDto> findSystemIpByCond(SystemIpRequestDto systemIpRequestDto, Pageable pageable);
}
