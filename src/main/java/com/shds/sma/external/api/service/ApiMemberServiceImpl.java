package com.shds.sma.external.api.service;

import com.shds.sma.apps.admin.client.entity.Client;
import com.shds.sma.apps.admin.member.entity.Member;
import com.shds.sma.apps.system.entity.System;
import com.shds.sma.apps.admin.client.repository.ClientRepository;
import com.shds.sma.apps.admin.member.repository.MemberRepository;
import com.shds.sma.apps.system.repository.SystemRepository;
import com.shds.sma.apps.admin.member.entity.type.EmpStatus;
import com.shds.sma.external.api.dto.member.ApiMemberModRequestDto;
import com.shds.sma.external.api.dto.member.ApiMemberSaveRequestDto;
import com.shds.sma.external.api.dto.member.ApiMemberResponseDto;
import com.shds.sma.common.exception.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.shds.sma.common.exception.ExceptionMessageConst.NOT_FOUND_MEMBER;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ApiMemberServiceImpl implements ApiMemberService {

    private final MemberRepository memberRepository;

    private final ClientRepository clientRepository;

    private final SystemRepository systemRepository;

    private final ModelMapper modelMapper;

    @Override
    public Page<ApiMemberResponseDto> getAllMembers(Pageable pageable) {
        Page<Member> members = memberRepository.findAll(pageable);
        return members.map(ApiMemberResponseDto::new);
    }

    @Override
    public ApiMemberResponseDto getMember(Long memberId) {
        Member member = getMemberById(memberId);
        return modelMapper.map(member, ApiMemberResponseDto.class);
    }

    @Override
    public ApiMemberResponseDto createMember(ApiMemberSaveRequestDto apiMemberSaveRequestDto) {
        Client findClient = clientRepository.findByClientCode(apiMemberSaveRequestDto.getClientCode());
        System findSystem = systemRepository.findBySystemName(apiMemberSaveRequestDto.getSystemName());

        Member saveMember = buildMember(apiMemberSaveRequestDto, findClient, findSystem);
        Member createdMember = memberRepository.save(saveMember);

        return modelMapper.map(createdMember, ApiMemberResponseDto.class);
    }

    @Override
    public ApiMemberResponseDto updateMember(ApiMemberModRequestDto apiMemberModRequestDto) {
        Client findClient = clientRepository.findByClientCode(apiMemberModRequestDto.getClientCode());
        apiMemberModRequestDto.setClient(findClient);

        System findSystem = systemRepository.findBySystemName(apiMemberModRequestDto.getSystemName());
        apiMemberModRequestDto.setSystem(findSystem);

        Member updatedMember = getMemberById(apiMemberModRequestDto.getMemberId());
        updatedMember.memberModified(apiMemberModRequestDto);

        return modelMapper.map(updatedMember, ApiMemberResponseDto.class);
    }

    @Override
    public void deleteMember(Long memberId) {
        Member deletedMember = getMemberById(memberId);
        deletedMember.empStatusChange(EmpStatus.RETIRE);
    }

    private Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new BizException(NOT_FOUND_MEMBER));
    }

    private static Member buildMember(ApiMemberSaveRequestDto apiMemberSaveRequestDto, Client findClient, System findSystem) {
        return Member.builder()
                .name(apiMemberSaveRequestDto.getName())
                .client(findClient)
                .deptCode(apiMemberSaveRequestDto.getDeptCode())
                .deptName(apiMemberSaveRequestDto.getDeptName())
                .gradeCode(apiMemberSaveRequestDto.getGradeCode())
                .gradeName(apiMemberSaveRequestDto.getGradeName())
                .roleCode(apiMemberSaveRequestDto.getRoleCode())
                .roleName(apiMemberSaveRequestDto.getRoleName())
                .mail(apiMemberSaveRequestDto.getMail())
                .phone(apiMemberSaveRequestDto.getPhone())
                .empStatus(apiMemberSaveRequestDto.getEmpStatus())
                .empAuth(apiMemberSaveRequestDto.getEmpAuth())
                .system(findSystem)
                .systemRole(apiMemberSaveRequestDto.getSystemRole()).build();
    }

}
