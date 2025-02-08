package com.shds.sma.cert.service;

import com.shds.sma.admin.dto.member.MemberResponseDto;
import com.shds.sma.admin.dto.system.SystemResponseDto;
import com.shds.sma.admin.service.AdminService;
import com.shds.sma.cert.dto.CertRequestDto;
import com.shds.sma.cert.dto.CertResponseDto;
import com.shds.sma.cert.entity.Cert;
import com.shds.sma.cert.repository.CertRepository;
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
public class CertServiceImpl implements CertService {

    private final CertRepository certRepository;

    private final AdminService adminService;

    private final ModelMapper modelMapper;

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
