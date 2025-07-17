package com.shds.sma.apps.admin.common.service;

import com.shds.sma.apps.admin.member.dto.MemberResponseDto;
import com.shds.sma.apps.admin.member.entity.Member;
import com.shds.sma.apps.admin.member.repository.MemberRepository;
import com.shds.sma.apps.system.dto.SystemModRequestDto;
import com.shds.sma.apps.system.entity.System;
import com.shds.sma.apps.system.dto.SystemRequestDto;
import com.shds.sma.apps.system.dto.SystemResponseDto;
import com.shds.sma.apps.system.dto.SystemSaveRequestDto;
import com.shds.sma.apps.system.repository.SystemRepository;
import com.shds.sma.common.exception.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.shds.sma.common.exception.ExceptionMessageConst.NOT_FOUND_SYSTEM;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SystemAdminServiceImpl implements SystemAdminService {

    private final SystemRepository systemRepository;

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SystemResponseDto> findSystemAll() {
        List<System> findSystem = systemRepository.findAll();
        return findSystem.stream().map(SystemResponseDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SystemResponseDto> findSystemByCond(SystemRequestDto systemRequestDto, Pageable pageable) {
        Page<System> systems = systemRepository.findSystemByCond(systemRequestDto, pageable);
        return systems.map(SystemResponseDto::new);
    }

    @Override
    @Transactional(readOnly = true)
    public SystemResponseDto findSystemById(Long systemId) {
        System findSystem = getSystem(systemId);
        return modelMapper.map(findSystem, SystemResponseDto.class);
    }

    @Override
    public void systemSave(SystemSaveRequestDto systemSaveRequestDto) {
        systemRepository.save(modelMapper.map(systemSaveRequestDto, System.class));
    }

    @Override
    public void modifiedSystem(SystemModRequestDto systemModRequestDto) {
        System findSystem = getSystem(systemModRequestDto.getSystemId());
        findSystem.systemModified(systemModRequestDto);
    }

    @Override
    public void removeSystem(Long systemId) {
        System findSystem = getSystem(systemId);
        findSystem.setValidityN();
    }

    @Override
    public void useSystem(Long systemId) {
        System findSystem = getSystem(systemId);
        findSystem.setValidityY();
    }

    private System getSystem(Long systemId) {
        return systemRepository.findById(systemId).orElseThrow(() -> new BizException(NOT_FOUND_SYSTEM));
    }
}
