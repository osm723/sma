package com.shds.sma.api.service;

import com.shds.sma.admin.entity.Client;
import com.shds.sma.admin.entity.Member;
import com.shds.sma.system.entity.System;
import com.shds.sma.admin.repository.client.ClientRepository;
import com.shds.sma.admin.repository.member.MemberRepository;
import com.shds.sma.system.repository.SystemRepository;
import com.shds.sma.admin.types.EmpStatus;
import com.shds.sma.api.dto.member.ApiMemberModRequestDto;
import com.shds.sma.api.dto.member.ApiMemberSaveRequestDto;
import com.shds.sma.api.dto.member.ApiMemberResponseDto;
import com.shds.sma.common.exception.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new BizException("존재하지 않는 직원입니다."));
        return modelMapper.map(member, ApiMemberResponseDto.class);
    }

    @Override
    public ApiMemberResponseDto createMember(ApiMemberSaveRequestDto apiMemberSaveRequestDto) {
        String clientCode = apiMemberSaveRequestDto.getClientCode();
        Client findClient = clientRepository.findByClientCode(clientCode);

        String systemName = apiMemberSaveRequestDto.getSystemName();
        System findSystem = systemRepository.findBySystemName(systemName);

        Member saveMember = Member.builder()
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

        Member createdMember = memberRepository.save(saveMember);
        return modelMapper.map(createdMember, ApiMemberResponseDto.class);
    }

    @Override
    public ApiMemberResponseDto updateMember(ApiMemberModRequestDto apiMemberModRequestDto) {
        String clientCode = apiMemberModRequestDto.getClientCode();
        Client findClient = clientRepository.findByClientCode(clientCode);
        apiMemberModRequestDto.setClient(findClient);

        String systemName = apiMemberModRequestDto.getSystemName();
        System findSystem = systemRepository.findBySystemName(systemName);
        apiMemberModRequestDto.setSystem(findSystem);

        Member updatedMember = memberRepository.findById(apiMemberModRequestDto.getMemberId()).orElseThrow(() -> new BizException("존재하지 않는 직원입니다."));
        updatedMember.memberModified(apiMemberModRequestDto);
        return modelMapper.map(updatedMember, ApiMemberResponseDto.class);
    }

    @Override
    public void deleteMember(Long memberId) {
        Member deletedMember = memberRepository.findById(memberId).orElseThrow(() -> new BizException("존재하지 않는 직원입니다."));
        deletedMember.empStatusChange(EmpStatus.RETIRE);
    }

}
