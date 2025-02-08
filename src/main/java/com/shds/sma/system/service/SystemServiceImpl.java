package com.shds.sma.system.service;

import com.shds.sma.admin.dto.system.SystemResponseDto;
import com.shds.sma.admin.entity.Member;
import com.shds.sma.admin.entity.System;
import com.shds.sma.admin.repositroy.member.MemberRepository;
import com.shds.sma.admin.repositroy.system.SystemRepository;
import com.shds.sma.cert.entity.Cert;
import com.shds.sma.ip.dto.IpDistinctResponseDto;
import com.shds.sma.cert.repository.CertRepository;
import com.shds.sma.ip.repository.IpRepository;
import com.shds.sma.system.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SystemServiceImpl implements SystemService {

    private final SystemRepository systemRepository;

    private final IpRepository ipRepository;

    private final CertRepository certRepository;

    private final MemberRepository memberRepository;

    @Override
    public List<SystemResponseDto> findSystemAll() {
        List<System> findSystem = systemRepository.findAll();
        return findSystem.stream().map(SystemResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public Page<SystemIpResponseDto> findSystemIpByCond(SystemIpRequestDto systemIpRequestDto, Pageable pageable) {
        //Page<Ip> findIp = ipRepository.findIpByCond(systemIpRequestDto, pageable);
        //return findIp.map(SystemIpResponseDto::new);
        return ipRepository.findSystemIpByCond(systemIpRequestDto, pageable);
    }

    @Override
    public Page<SystemCertResponseDto> findSystemCertByCond(SystemCertRequestDto systemCertRequestDto, Pageable pageable) {
        //Page<Cert> findCert = certRepository.findCertByCond(systemCertRequestDto, pageable);
        //return findCert.map(SystemCertResponseDto::new);
        return certRepository.findSystemCertByCond(systemCertRequestDto, pageable);
    }

    @Override
    public Page<SystemManagerResponseDto> findSystemMemberByCond(SystemManagerRequestDto systemManagerRequestDto, Pageable pageable) {
        Page<Member> findMember = memberRepository.findSystemMemberByCond(systemManagerRequestDto, pageable);
        return findMember.map(SystemManagerResponseDto::new);
    }
}
