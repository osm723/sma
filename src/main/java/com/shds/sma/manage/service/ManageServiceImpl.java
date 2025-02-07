package com.shds.sma.manage.service;

import com.shds.sma.manage.dto.ip.IpModRequestDto;
import com.shds.sma.manage.dto.ip.IpSaveRequestDto;
import com.shds.sma.admin.dto.member.MemberResponseDto;
import com.shds.sma.admin.dto.system.SystemResponseDto;
import com.shds.sma.admin.entity.Member;
import com.shds.sma.admin.entity.System;
import com.shds.sma.admin.repositroy.member.MemberRepository;
import com.shds.sma.admin.repositroy.system.SystemRepository;
import com.shds.sma.admin.service.AdminService;
import com.shds.sma.manage.dto.cert.CertRequestDto;
import com.shds.sma.manage.dto.cert.CertResponseDto;
import com.shds.sma.manage.dto.ip.IpRequestDto;
import com.shds.sma.manage.dto.ip.IpResponseDto;
import com.shds.sma.manage.entity.Cert;
import com.shds.sma.manage.entity.Ip;
import com.shds.sma.manage.repository.cert.CertRepository;
import com.shds.sma.manage.repository.ip.IpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ManageServiceImpl implements ManageService {

    private final IpRepository ipRepository;

    private final CertRepository certRepository;

    private final AdminService adminService;

    private final SystemRepository systemRepository;

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    @Override
    public Page<IpResponseDto> findIpByCond(IpRequestDto ipRequestDto, Pageable pageable) {
        Page<Ip> findIp = ipRepository.findIpByCond(ipRequestDto, pageable);
        return findIp.map(IpResponseDto::new);
    }

    @Override
    public IpResponseDto findIpById(Long ipId) {
        Ip findIp = ipRepository.findById(ipId).get();
        return modelMapper.map(findIp, IpResponseDto.class);
    }

    @Override
    public void saveIp(IpSaveRequestDto ipSaveRequestDto) {
        Long systemId = ipSaveRequestDto.getApplySystemId();
        System findSystem = systemRepository.findById(systemId).get();

        Long memberId = ipSaveRequestDto.getMemberId();
        Member findMember = memberRepository.findById(memberId).get();

        Ip saveIp = Ip.builder()
                .ipType(ipSaveRequestDto.getIpType())
                .startIpAddr(ipSaveRequestDto.getStartIpAddr())
                .endIpAddr(ipSaveRequestDto.getEndIpAddr())
                .applySystem(findSystem)
                .content(ipSaveRequestDto.getContent())
                .siteLink(ipSaveRequestDto.getSiteLink())
                .startDate(ipSaveRequestDto.getStartDate())
                .endDate(ipSaveRequestDto.getEndDate())
                .member(findMember)
                .approval(ipSaveRequestDto.getApproval()).build();

        ipRepository.save(saveIp);
    }

    @Override
    public void modifiedIp(IpModRequestDto ipModRequestDto) {
        Ip findIp = ipRepository.findById(ipModRequestDto.getIpId()).get();

        Long systemId = ipModRequestDto.getApplySystemId();
        System findSystem = systemRepository.findById(systemId).get();
        ipModRequestDto.setApplySystem(findSystem);

        Long memberId = ipModRequestDto.getMemberId();
        Member findMember = memberRepository.findById(memberId).get();
        ipModRequestDto.setMember(findMember);

        findIp.ipModified(ipModRequestDto);
    }

    @Override
    public void removeIp(Long ipId) {
        Ip findIp = ipRepository.findById(ipId).get();
        findIp.setValidityN();
    }

    @Override
    public void useIp(Long ipId) {
        Ip findIp = ipRepository.findById(ipId).get();
        findIp.setValidityY();
    }

    @Override
    public Page<CertResponseDto> findCertByCond(CertRequestDto certRequestDto, Pageable pageable) {
        Page<Cert> findCert = certRepository.findCertByCond(certRequestDto, pageable);
        return findCert.map(CertResponseDto::new);
    }

    @Override
    public CertResponseDto findCertById(Long certId) {
        Cert findCert = certRepository.findById(certId).get();
        return modelMapper.map(findCert, CertResponseDto.class);
    }

    @Override
    public List<MemberResponseDto> findMemberAll() {
        return adminService.findMemberAll();
    }

    @Override
    public List<SystemResponseDto> findSystemAll() {
        return adminService.findSystemAll();
    }




}
