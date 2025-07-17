package com.shds.sma.apps.admin.member.service;

import com.shds.sma.apps.admin.member.dto.MemberModRequestDto;
import com.shds.sma.apps.admin.member.dto.MemberRequestDto;
import com.shds.sma.apps.admin.member.dto.MemberResponseDto;
import com.shds.sma.apps.admin.member.dto.MemberSaveRequestDto;
import com.shds.sma.apps.admin.client.entity.Client;
import com.shds.sma.apps.admin.member.entity.Member;
import com.shds.sma.apps.admin.client.repository.ClientRepository;
import com.shds.sma.apps.admin.member.repository.MemberRepository;
import com.shds.sma.apps.admin.member.entity.type.EmpStatus;
import com.shds.sma.apps.system.entity.System;
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

import static com.shds.sma.common.exception.ExceptionMessageConst.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberAdminServiceImpl implements MemberAdminService {

    private final MemberRepository memberRepository;

    private final SystemRepository systemRepository;

    private final ClientRepository clientRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<MemberResponseDto> findMemberAll() {
        List<Member> findMember = memberRepository.findAll();
        return findMember.stream().map(MemberResponseDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MemberResponseDto> findMemberByCond(MemberRequestDto memberRequestDto, Pageable pageable) {
        Page<Member> findMember = memberRepository.findMemberByCond(memberRequestDto, pageable);
        return findMember.map(MemberResponseDto::new);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberResponseDto findMemberById(Long memberId) {
        Member member = getMember(memberId);
        return modelMapper.map(member, MemberResponseDto.class);
    }

    @Override
    public void saveMember(MemberSaveRequestDto memberSaveRequestDto) {
        System findSystem = getSystem(memberSaveRequestDto.getSystemId());
        memberSaveRequestDto.setSystem(findSystem);

        Client findClient = getClient(memberSaveRequestDto.getClientId());
        memberSaveRequestDto.setClient(findClient);

        memberRepository.save(buildMember(memberSaveRequestDto, findClient, findSystem));
    }

    @Override
    public void modifiedMember(MemberModRequestDto memberModRequestDto) {
        System findSystem = getSystem(memberModRequestDto.getSystemId());
        memberModRequestDto.setSystem(findSystem);

        Client findClient = getClient(memberModRequestDto.getClientId());
        memberModRequestDto.setClient(findClient);

        Member findMember = getMember(memberModRequestDto.getMemberId());
        findMember.memberModified(memberModRequestDto);
    }

    @Override
    public void memberChangeStatus(Long memberId, EmpStatus empStatus) {
        Member findMember = getMember(memberId);
        findMember.empStatusChange(empStatus);
    }

    private Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new BizException(NOT_FOUND_MEMBER));
    }

    private System getSystem(Long systemId) {
        return systemRepository.findById(systemId).orElseThrow(() -> new BizException(NOT_FOUND_SYSTEM));
    }

    private Client getClient(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> new BizException(NOT_FOUND_CLIENT));
    }

    private static Member buildMember(MemberSaveRequestDto memberSaveRequestDto, Client findClient, System findSystem) {
        return Member.builder()
                .name(memberSaveRequestDto.getName())
                .client(findClient)
                .deptCode(memberSaveRequestDto.getDeptCode())
                .deptName(memberSaveRequestDto.getDeptName())
                .gradeCode(memberSaveRequestDto.getGradeCode())
                .gradeName(memberSaveRequestDto.getGradeName())
                .roleCode(memberSaveRequestDto.getRoleCode())
                .roleName(memberSaveRequestDto.getRoleName())
                .mail(memberSaveRequestDto.getMail())
                .phone(memberSaveRequestDto.getPhone())
                .empStatus(memberSaveRequestDto.getEmpStatus())
                .empAuth(memberSaveRequestDto.getEmpAuth())
                .system(findSystem)
                .systemRole(memberSaveRequestDto.getSystemRole()).build();
    }
}
