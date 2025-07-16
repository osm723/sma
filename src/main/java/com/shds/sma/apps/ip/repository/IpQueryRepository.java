package com.shds.sma.apps.ip.repository;

import com.shds.sma.apps.ip.dto.IpRequestDto;
import com.shds.sma.apps.ip.entity.Ip;
import com.shds.sma.apps.system.dto.SystemIpRequestDto;
import com.shds.sma.apps.system.dto.SystemIpResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IpQueryRepository {
    Page<Ip> findIpByCond(IpRequestDto ipRequestDto, Pageable pageable);

    Page<SystemIpResponseDto> findSystemIpByCond(SystemIpRequestDto systemIpRequestDto, Pageable pageable);

    List<Ip> findIpPreExpiration();

    boolean isIpReApply(Ip ip);

    List<Ip> findIpPreDayExpiration(Long preDay);
}
