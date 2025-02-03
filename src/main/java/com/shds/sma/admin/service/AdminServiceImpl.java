package com.shds.sma.admin.service;

import com.shds.sma.admin.dto.client.ClientModRequestDto;
import com.shds.sma.admin.dto.client.ClientRequestDto;
import com.shds.sma.admin.dto.client.ClientResponseDto;
import com.shds.sma.admin.dto.client.ClientSaveRequestDto;
import com.shds.sma.admin.dto.member.MemberModRequestDto;
import com.shds.sma.admin.dto.member.MemberRequestDto;
import com.shds.sma.admin.dto.member.MemberResponseDto;
import com.shds.sma.admin.dto.member.MemberSaveRequestDto;
import com.shds.sma.admin.dto.notice.*;
import com.shds.sma.admin.dto.system.SystemModRequestDto;
import com.shds.sma.admin.dto.system.SystemRequestDto;
import com.shds.sma.admin.dto.system.SystemResponseDto;
import com.shds.sma.admin.dto.system.SystemSaveRequestDto;
import com.shds.sma.admin.entity.Client;
import com.shds.sma.admin.entity.Member;
import com.shds.sma.admin.entity.Notice;
import com.shds.sma.admin.entity.System;
import com.shds.sma.admin.entity.types.EmpAuth;
import com.shds.sma.admin.entity.types.EmpStatus;
import com.shds.sma.admin.entity.types.SystemRole;
import com.shds.sma.admin.repositroy.client.ClientRepository;
import com.shds.sma.admin.repositroy.member.MemberRepository;
import com.shds.sma.admin.repositroy.notice.NoticeRepository;
import com.shds.sma.admin.repositroy.system.SystemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final NoticeRepository noticeRepository;

    private final SystemRepository systemRepository;

    private final ClientRepository clientRepository;

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    @Override
    public Page<NoticeResponseDto> findNoticeAll(Pageable pageable) {
        Page<Notice> notices = noticeRepository.findAll(pageable);
        return notices.map(NoticeResponseDto::new);
    }

    @Override
    public Page<NoticeResponseDto> findNoticeCond(NoticeCondRequestDto noticeCondRequestDto, Pageable pageable) {
        Page<Notice> notices = noticeRepository.findNoticeCond(noticeCondRequestDto, pageable);
        return notices.map(NoticeResponseDto::new);
    }

    @Override
    public Page<NoticeHomeResponseDto> findHomeNotice(Pageable pageable) {
        Page<Notice> notices = noticeRepository.findHomeNotice(pageable);
        return notices.map(NoticeHomeResponseDto::new);
    }

    @Override
    public NoticeResponseDto findNoticeById(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).get();
        return modelMapper.map(notice, NoticeResponseDto.class);
    }

    @Override
    public void saveNotice(NoticeSaveRequestDto noticeSaveRequestDto) {
        noticeRepository.save(modelMapper.map(noticeSaveRequestDto, Notice.class));
    }

    @Override
    public void modifiedNotice(NoticeModRequestDto noticeModRequestDto) {
        Notice findNotice = noticeRepository.findById(noticeModRequestDto.getNoticeId()).get();
        findNotice.noticeModified(noticeModRequestDto);
    }

    @Override
    public void removeNotice(Long noticeId) {
        Notice findNotice = noticeRepository.findById(noticeId).get();
        findNotice.setValidityN();
    }

    @Override
    public void useNotice(Long noticeId) {
        Notice findNotice = noticeRepository.findById(noticeId).get();
        findNotice.setValidityY();
    }

    @Override
    public List<SystemResponseDto> findSystemAll() {
        List<System> findSystem = systemRepository.findAll();
        return findSystem.stream().map(SystemResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public Page<SystemResponseDto> findSystemByCond(SystemRequestDto systemRequestDto, Pageable pageable) {
        Page<System> systems = systemRepository.findSystemByCond(systemRequestDto, pageable);
        return systems.map(SystemResponseDto::new);
    }

    @Override
    public SystemResponseDto findSystemById(Long systemId) {
        System findSystem = systemRepository.findById(systemId).get();
        return modelMapper.map(findSystem, SystemResponseDto.class);
    }

    @Override
    public void systemSave(SystemSaveRequestDto systemSaveRequestDto) {
        systemRepository.save(modelMapper.map(systemSaveRequestDto, System.class));
    }

    @Override
    public void modifiedSystem(SystemModRequestDto systemModRequestDto) {
        System findSystem = systemRepository.findById(systemModRequestDto.getSystemId()).get();
        findSystem.systemModified(systemModRequestDto);
    }

    @Override
    public void removeSystem(Long systemId) {
        System findSystem = systemRepository.findById(systemId).get();
        findSystem.setValidityN();
    }

    @Override
    public void useSystem(Long systemId) {
        System findSystem = systemRepository.findById(systemId).get();
        findSystem.setValidityY();
    }

    @Override
    public List<ClientResponseDto> findClientAll() {
        List<Client> findClient = clientRepository.findAll();
        return findClient.stream().map(ClientResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public Page<ClientResponseDto> findClientByCond(ClientRequestDto clientRequestDto, Pageable pageable) {
        Page<Client> clients = clientRepository.findClientByCond(clientRequestDto, pageable);
        return clients.map(ClientResponseDto::new);
    }

    @Override
    public ClientResponseDto findClientById(Long clientId) {
        Client findClient = clientRepository.findById(clientId).get();
        return modelMapper.map(findClient, ClientResponseDto.class);
    }

    @Override
    public void saveClient(ClientSaveRequestDto clientSaveRequestDto) {
        clientRepository.save(modelMapper.map(clientSaveRequestDto, Client.class));
    }

    @Override
    public void modifiedClient(ClientModRequestDto clientModRequestDto) {
        Client findClient = clientRepository.findById(clientModRequestDto.getClientId()).get();
        findClient.clientModified(clientModRequestDto);
    }

    @Override
    public void removeClient(Long clientId) {
        Client findClient = clientRepository.findById(clientId).get();
        findClient.setValidityN();
    }

    @Override
    public void useClient(Long clientId) {
        Client findClient = clientRepository.findById(clientId).get();
        findClient.setValidityY();
    }

    @Override
    public Page<MemberResponseDto> findMemberCond(MemberRequestDto memberRequestDto, Pageable pageable) {
        Page<Member> findMember = memberRepository.findMemberByCond(memberRequestDto, pageable);
        return findMember.map(MemberResponseDto::new);
    }

    @Override
    public MemberResponseDto findMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        return modelMapper.map(member, MemberResponseDto.class);
    }

    @Override
    public void saveMember(MemberSaveRequestDto memberSaveRequestDto) {
        Long systemId = memberSaveRequestDto.getSystemId();
        System findSystem = systemRepository.findById(systemId).get();
        memberSaveRequestDto.setSystem(findSystem);

        Long clientId = memberSaveRequestDto.getClientId();
        Client findClient = clientRepository.findById(clientId).get();
        memberSaveRequestDto.setClient(findClient);

        Member saveMember = Member.builder()
                .name(memberSaveRequestDto.getName())
                .client(findClient)
                .deptCode(memberSaveRequestDto.getDeptCode())
                .deptName(memberSaveRequestDto.getDeptName())
                .gradeCode(memberSaveRequestDto.getGradeCode())
                .gradeName(memberSaveRequestDto.getGradeName())
                .roleCode(memberSaveRequestDto.getRoleCode())
                .roleName(memberSaveRequestDto.getRoleName())
                .empStatus(memberSaveRequestDto.getEmpStatus())
                .empAuth(memberSaveRequestDto.getEmpAuth())
                .system(findSystem)
                .systemRole(memberSaveRequestDto.getSystemRole()).build();

        memberRepository.save(saveMember);
    }

    @Override
    public void modifiedMember(MemberModRequestDto memberModRequestDto) {
        Member findMember = memberRepository.findById(memberModRequestDto.getMemberId()).get();
        findMember.memberModified(memberModRequestDto);
    }

    @Override
    public void memberChangeStatus(Long memberId, EmpStatus empStatus) {
        Member findMember = memberRepository.findById(memberId).get();
        findMember.empStatusChange(empStatus);
    }

}
