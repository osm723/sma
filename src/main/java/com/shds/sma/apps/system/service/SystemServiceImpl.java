package com.shds.sma.apps.system.service;

import com.shds.sma.apps.system.dto.*;
import com.shds.sma.apps.admin.entity.Member;
import com.shds.sma.apps.system.entity.System;
import com.shds.sma.apps.admin.repository.member.MemberRepository;
import com.shds.sma.apps.system.repository.SystemRepository;
import com.shds.sma.apps.cert.repository.CertRepository;
import com.shds.sma.apps.ip.repository.IpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
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
        return ipRepository.findSystemIpByCond(systemIpRequestDto, pageable);
    }

    @Override
    public Page<SystemCertResponseDto> findSystemCertByCond(SystemCertRequestDto systemCertRequestDto, Pageable pageable) {
        return certRepository.findSystemCertByCond(systemCertRequestDto, pageable);
    }

    @Override
    public Page<SystemManagerResponseDto> findSystemMemberByCond(SystemManagerRequestDto systemManagerRequestDto, Pageable pageable) {
        Page<Member> findMember = memberRepository.findSystemMemberByCond(systemManagerRequestDto, pageable);
        return findMember.map(SystemManagerResponseDto::new);
    }
}
