package com.shds.sma.manage.service;

import com.shds.sma.manage.dto.ip.IpModRequestDto;
import com.shds.sma.manage.dto.ip.IpSaveRequestDto;
import com.shds.sma.admin.dto.member.MemberResponseDto;
import com.shds.sma.admin.dto.system.SystemResponseDto;
import com.shds.sma.manage.dto.cert.CertRequestDto;
import com.shds.sma.manage.dto.cert.CertResponseDto;
import com.shds.sma.manage.dto.ip.IpRequestDto;
import com.shds.sma.manage.dto.ip.IpResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ManageService {
    Page<IpResponseDto> findIpByCond(IpRequestDto ipRequestDto, Pageable pageable);

    IpResponseDto findIpById(Long ipId);

    void saveIp(IpSaveRequestDto ipSaveRequestDto);

    void modifiedIp(IpModRequestDto ipModRequestDto);

    void removeIp(Long ipId);

    void useIp(Long ipId);

    Page<CertResponseDto> findCertByCond(CertRequestDto certRequestDto, Pageable pageable);

    List<MemberResponseDto> findMemberAll();

    List<SystemResponseDto> findSystemAll();

    CertResponseDto findCertById(Long certId);
}
