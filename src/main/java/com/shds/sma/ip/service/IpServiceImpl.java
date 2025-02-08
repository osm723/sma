package com.shds.sma.ip.service;

import com.shds.sma.admin.dto.member.MemberResponseDto;
import com.shds.sma.admin.dto.system.SystemResponseDto;
import com.shds.sma.admin.service.AdminService;
import com.shds.sma.ip.dto.IpRequestDto;
import com.shds.sma.ip.dto.IpResponseDto;
import com.shds.sma.ip.entity.Ip;
import com.shds.sma.ip.repository.IpRepository;
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
public class IpServiceImpl implements IpService {

    private final IpRepository ipRepository;

    private final AdminService adminService;

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
    public List<MemberResponseDto> findMemberAll() {
        return adminService.findMemberAll();
    }

    @Override
    public List<SystemResponseDto> findSystemAll() {
        return adminService.findSystemAll();
    }




}
