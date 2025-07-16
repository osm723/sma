package com.shds.sma.apps.ip.service;

import com.shds.sma.apps.admin.dto.member.MemberResponseDto;
import com.shds.sma.apps.system.dto.SystemResponseDto;
import com.shds.sma.apps.ip.dto.IpRequestDto;
import com.shds.sma.apps.ip.dto.IpResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IpService {
    Page<IpResponseDto> findIpByCond(IpRequestDto ipRequestDto, Pageable pageable);

    IpResponseDto findIpById(Long ipId);

    List<MemberResponseDto> findMemberAll();

    List<SystemResponseDto> findSystemAll();

    void getIpPreExpiration();

    void getIpPreExpirationToManager();
}
