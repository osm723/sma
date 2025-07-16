package com.shds.sma.apps.admin.service;

import com.shds.sma.apps.admin.dto.client.ClientModRequestDto;
import com.shds.sma.apps.admin.dto.client.ClientRequestDto;
import com.shds.sma.apps.admin.dto.client.ClientResponseDto;
import com.shds.sma.apps.admin.dto.client.ClientSaveRequestDto;
import com.shds.sma.apps.admin.dto.notice.*;
import com.shds.sma.apps.admin.repository.approval.ApprovalRepository;
import com.shds.sma.apps.cert.dto.CertModRequestDto;
import com.shds.sma.apps.cert.dto.CertSaveRequestDto;
import com.shds.sma.apps.admin.entity.Approval;
import com.shds.sma.common.exception.BizException;
import com.shds.sma.apps.ip.dto.IpModRequestDto;
import com.shds.sma.apps.ip.dto.IpSaveRequestDto;
import com.shds.sma.apps.admin.dto.member.MemberModRequestDto;
import com.shds.sma.apps.admin.dto.member.MemberRequestDto;
import com.shds.sma.apps.admin.dto.member.MemberResponseDto;
import com.shds.sma.apps.admin.dto.member.MemberSaveRequestDto;
import com.shds.sma.apps.system.dto.SystemModRequestDto;
import com.shds.sma.apps.system.dto.SystemRequestDto;
import com.shds.sma.apps.system.dto.SystemResponseDto;
import com.shds.sma.apps.system.dto.SystemSaveRequestDto;
import com.shds.sma.apps.admin.entity.Client;
import com.shds.sma.apps.admin.entity.Member;
import com.shds.sma.apps.admin.entity.Notice;
import com.shds.sma.apps.system.entity.System;
import com.shds.sma.apps.admin.types.EmpStatus;
import com.shds.sma.apps.admin.repository.client.ClientRepository;
import com.shds.sma.apps.admin.repository.member.MemberRepository;
import com.shds.sma.apps.admin.repository.notice.NoticeRepository;
import com.shds.sma.apps.system.repository.SystemRepository;
import com.shds.sma.apps.cert.dto.CertRequestDto;
import com.shds.sma.apps.cert.dto.CertResponseDto;
import com.shds.sma.apps.ip.dto.IpRequestDto;
import com.shds.sma.apps.ip.dto.IpResponseDto;
import com.shds.sma.apps.cert.entity.Cert;
import com.shds.sma.apps.ip.entity.Ip;
import com.shds.sma.apps.cert.repository.CertRepository;
import com.shds.sma.apps.ip.repository.IpRepository;
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
public class AdminServiceImpl implements AdminService {

    private final NoticeRepository noticeRepository;

    private final SystemRepository systemRepository;

    private final ClientRepository clientRepository;

    private final MemberRepository memberRepository;

    private final IpRepository ipRepository;

    private final CertRepository certRepository;

    private final ApprovalRepository approvalRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<NoticeResponseDto> findNoticeAll(Pageable pageable) {
        Page<Notice> notices = noticeRepository.findAll(pageable);
        return notices.map(NoticeResponseDto::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NoticeResponseDto> findNoticeByCond(NoticeCondRequestDto noticeCondRequestDto, Pageable pageable) {
        Page<Notice> notices = noticeRepository.findNoticeByCond(noticeCondRequestDto, pageable);
        return notices.map(NoticeResponseDto::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NoticeHomeResponseDto> findHomeNotice(Pageable pageable) {
        Page<Notice> notices = noticeRepository.findHomeNotice(pageable);
        return notices.map(NoticeHomeResponseDto::new);
    }

    @Override
    @Transactional(readOnly = true)
    public NoticeResponseDto findNoticeById(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new BizException(NOT_FOUND_NOTICE));
        return modelMapper.map(notice, NoticeResponseDto.class);
    }

    @Override
    public void saveNotice(NoticeSaveRequestDto noticeSaveRequestDto) {
        noticeRepository.save(modelMapper.map(noticeSaveRequestDto, Notice.class));
    }

    @Override
    public void modifiedNotice(NoticeModRequestDto noticeModRequestDto) {
        Notice findNotice = noticeRepository.findById(noticeModRequestDto.getNoticeId()).orElseThrow(() -> new BizException(NOT_FOUND_NOTICE));
        findNotice.noticeModified(noticeModRequestDto);
    }

    @Override
    public void removeNotice(Long noticeId) {
        Notice findNotice = noticeRepository.findById(noticeId).orElseThrow(() -> new BizException(NOT_FOUND_NOTICE));
        findNotice.setValidityN();
    }

    @Override
    public void useNotice(Long noticeId) {
        Notice findNotice = noticeRepository.findById(noticeId).orElseThrow(() -> new BizException(NOT_FOUND_NOTICE));
        findNotice.setValidityY();
    }

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
        System findSystem = systemRepository.findById(systemId).orElseThrow(() -> new BizException(NOT_FOUND_SYSTEM));
        return modelMapper.map(findSystem, SystemResponseDto.class);
    }

    @Override
    public void systemSave(SystemSaveRequestDto systemSaveRequestDto) {
        systemRepository.save(modelMapper.map(systemSaveRequestDto, System.class));
    }

    @Override
    public void modifiedSystem(SystemModRequestDto systemModRequestDto) {
        System findSystem = systemRepository.findById(systemModRequestDto.getSystemId()).orElseThrow(() -> new BizException(NOT_FOUND_SYSTEM));
        findSystem.systemModified(systemModRequestDto);
    }

    @Override
    public void removeSystem(Long systemId) {
        System findSystem = systemRepository.findById(systemId).orElseThrow(() -> new BizException(NOT_FOUND_SYSTEM));
        findSystem.setValidityN();
    }

    @Override
    public void useSystem(Long systemId) {
        System findSystem = systemRepository.findById(systemId).orElseThrow(() -> new BizException(NOT_FOUND_SYSTEM));
        findSystem.setValidityY();
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public ClientResponseDto findClientById(Long clientId) {
        Client findClient = clientRepository.findById(clientId).orElseThrow(() -> new BizException(NOT_FOUND_CLIENT));
        return modelMapper.map(findClient, ClientResponseDto.class);
    }

    @Override
    public void saveClient(ClientSaveRequestDto clientSaveRequestDto) {
        clientRepository.save(modelMapper.map(clientSaveRequestDto, Client.class));
    }

    @Override
    public void modifiedClient(ClientModRequestDto clientModRequestDto) {
        Client findClient = clientRepository.findById(clientModRequestDto.getClientId()).orElseThrow(() -> new BizException(NOT_FOUND_CLIENT));
        findClient.clientModified(clientModRequestDto);
    }

    @Override
    public void removeClient(Long clientId) {
        Client findClient = clientRepository.findById(clientId).orElseThrow(() -> new BizException(NOT_FOUND_CLIENT));
        findClient.setValidityN();
    }

    @Override
    public void useClient(Long clientId) {
        Client findClient = clientRepository.findById(clientId).orElseThrow(() -> new BizException(NOT_FOUND_CLIENT));
        findClient.setValidityY();
    }

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
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new BizException(NOT_FOUND_MEMBER));
        return modelMapper.map(member, MemberResponseDto.class);
    }

    @Override
    public void saveMember(MemberSaveRequestDto memberSaveRequestDto) {
        Long systemId = memberSaveRequestDto.getSystemId();
        System findSystem = systemRepository.findById(systemId).orElseThrow(() -> new BizException(NOT_FOUND_SYSTEM));
        memberSaveRequestDto.setSystem(findSystem);

        Long clientId = memberSaveRequestDto.getClientId();
        Client findClient = clientRepository.findById(clientId).orElseThrow(() -> new BizException(NOT_FOUND_CLIENT));
        memberSaveRequestDto.setClient(findClient);

        memberRepository.save(buildMember(memberSaveRequestDto, findClient, findSystem));
    }

    @Override
    public void modifiedMember(MemberModRequestDto memberModRequestDto) {
        Long systemId = memberModRequestDto.getSystemId();
        System findSystem = systemRepository.findById(systemId).orElseThrow(() -> new BizException(NOT_FOUND_SYSTEM));
        memberModRequestDto.setSystem(findSystem);

        Long clientId = memberModRequestDto.getClientId();
        Client findClient = clientRepository.findById(clientId).orElseThrow(() -> new BizException(NOT_FOUND_CLIENT));
        memberModRequestDto.setClient(findClient);

        Member findMember = memberRepository.findById(memberModRequestDto.getMemberId()).orElseThrow(() -> new BizException(NOT_FOUND_MEMBER));
        findMember.memberModified(memberModRequestDto);
    }

    @Override
    public void memberChangeStatus(Long memberId, EmpStatus empStatus) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new BizException(NOT_FOUND_MEMBER));
        findMember.empStatusChange(empStatus);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<IpResponseDto> findIpByCond(IpRequestDto ipRequestDto, Pageable pageable) {
        Page<Ip> findIp = ipRepository.findIpByCond(ipRequestDto, pageable);
        return findIp.map(IpResponseDto::new);
    }

    @Override
    @Transactional(readOnly = true)
    public IpResponseDto findIpById(Long ipId) {
        Ip findIp = ipRepository.findById(ipId).orElseThrow(() -> new BizException(NOT_FOUND_IP));
        return modelMapper.map(findIp, IpResponseDto.class);
    }

    @Override
    public void saveIp(IpSaveRequestDto ipSaveRequestDto) {
        Approval savedApproval = new Approval();
        if (ipSaveRequestDto.useApproval()) {
            savedApproval = saveIpApproval(ipSaveRequestDto);
        }

        Long systemId = ipSaveRequestDto.getApplySystemId();
        System findSystem = systemRepository.findById(systemId).orElseThrow(() -> new BizException(NOT_FOUND_SYSTEM));

        Long memberId = ipSaveRequestDto.getMemberId();
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new BizException(NOT_FOUND_MEMBER));

        ipRepository.save(buildIp(ipSaveRequestDto, findSystem, findMember, savedApproval));
    }

    @Override
    public void modifiedIp(IpModRequestDto ipModRequestDto) {
        if (ipModRequestDto.getApprovalId() != null) {
            Approval findApproval = approvalRepository.findById(ipModRequestDto.getApprovalId()).orElseThrow(() -> new BizException(NOT_FOUND_APPROVAL));
            findApproval.approvalIpModified(ipModRequestDto);
            ipModRequestDto.setApproval(findApproval);
        }

        Ip findIp = ipRepository.findById(ipModRequestDto.getIpId()).orElseThrow(() -> new BizException(NOT_FOUND_IP));

        Long systemId = ipModRequestDto.getApplySystemId();
        System findSystem = systemRepository.findById(systemId).orElseThrow(() -> new BizException(NOT_FOUND_SYSTEM));
        ipModRequestDto.setApplySystem(findSystem);

        Long memberId = ipModRequestDto.getMemberId();
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new BizException(NOT_FOUND_MEMBER));
        ipModRequestDto.setMember(findMember);

        findIp.ipModified(ipModRequestDto);
    }

    @Override
    public void removeIp(Long ipId) {
        Ip findIp = ipRepository.findById(ipId).orElseThrow(() -> new BizException(NOT_FOUND_IP));
        findIp.setValidityN();
    }

    @Override
    public void useIp(Long ipId) {
        Ip findIp = ipRepository.findById(ipId).orElseThrow(() -> new BizException(NOT_FOUND_IP));
        findIp.setValidityY();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CertResponseDto> findCertByCond(CertRequestDto certRequestDto, Pageable pageable) {
        Page<Cert> findCert = certRepository.findCertByCond(certRequestDto, pageable);
        return findCert.map(CertResponseDto::new);
    }

    @Override
    @Transactional(readOnly = true)
    public CertResponseDto findCertById(Long certId) {
        Cert findCert = certRepository.findById(certId).orElseThrow(() -> new BizException(NOT_FOUND_CERT));
        return modelMapper.map(findCert, CertResponseDto.class);
    }

    @Override
    public void saveCert(CertSaveRequestDto certSaveRequestDto) {
        Approval savedApproval = new Approval();
        if (certSaveRequestDto.useApproval()) {
            savedApproval = saveCertApproval(certSaveRequestDto);
        }

        Long systemId = certSaveRequestDto.getApplySystemId();
        System findSystem = systemRepository.findById(systemId).orElseThrow(() -> new BizException(NOT_FOUND_SYSTEM));

        Long memberId = certSaveRequestDto.getMemberId();
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new BizException(NOT_FOUND_MEMBER));

        certRepository.save(buildCert(certSaveRequestDto, findSystem, findMember, savedApproval));
    }

    @Override
    public void modifiedCert(CertModRequestDto certModRequestDto) {
        if (certModRequestDto.getApprovalId() != null) {
            Approval findApproval = approvalRepository.findById(certModRequestDto.getApprovalId()).orElseThrow(() -> new BizException(NOT_FOUND_APPROVAL));
            findApproval.approvalCertModified(certModRequestDto);
            certModRequestDto.setApproval(findApproval);
        }

        Cert findCert = certRepository.findById(certModRequestDto.getCertId()).orElseThrow(() -> new BizException(NOT_FOUND_CERT));

        Long systemId = certModRequestDto.getApplySystemId();
        System findSystem = systemRepository.findById(systemId).orElseThrow(() -> new BizException(NOT_FOUND_SYSTEM));
        certModRequestDto.setApplySystem(findSystem);

        Long memberId = certModRequestDto.getMemberId();
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new BizException(NOT_FOUND_MEMBER));
        certModRequestDto.setMember(findMember);

        findCert.certModified(certModRequestDto);
    }

    @Override
    public void removeCert(Long certId) {
        Cert findCert = certRepository.findById(certId).orElseThrow(() -> new BizException(NOT_FOUND_CERT));
        findCert.setValidityN();
    }

    @Override
    public void useCert(Long certId) {
        Cert findCert = certRepository.findById(certId).orElseThrow(() -> new BizException(NOT_FOUND_CERT));
        findCert.setValidityY();
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

    private Approval saveIpApproval(IpSaveRequestDto ipSaveRequestDto) {
        Approval saveApproval = Approval.builder()
                .approvalNo(ipSaveRequestDto.getApprovalNo())
                .drafterId(ipSaveRequestDto.getDrafterId())
                .degree(ipSaveRequestDto.getDegree())
                .approverId(ipSaveRequestDto.getApproverId())
                .approvalStatus(ipSaveRequestDto.getApprovalStatus())
                .approveDate(ipSaveRequestDto.getApproveDate())
                .cancelDate(ipSaveRequestDto.getCancelDate()).build();

        return approvalRepository.save(saveApproval);
    }

    private Approval saveCertApproval(CertSaveRequestDto certSaveRequestDto) {
        Approval saveApproval = Approval.builder()
                .approvalNo(certSaveRequestDto.getApprovalNo())
                .drafterId(certSaveRequestDto.getDrafterId())
                .degree(certSaveRequestDto.getDegree())
                .approverId(certSaveRequestDto.getApproverId())
                .approvalStatus(certSaveRequestDto.getApprovalStatus())
                .approveDate(certSaveRequestDto.getApproveDate())
                .cancelDate(certSaveRequestDto.getCancelDate()).build();

        return approvalRepository.save(saveApproval);
    }

    private static Ip buildIp(IpSaveRequestDto ipSaveRequestDto, System findSystem, Member findMember, Approval savedApproval) {
        return Ip.builder()
                .ipType(ipSaveRequestDto.getIpType())
                .startIpAddr(ipSaveRequestDto.getStartIpAddr())
                .endIpAddr(ipSaveRequestDto.getEndIpAddr())
                .port(ipSaveRequestDto.getPort())
                .applySystem(findSystem)
                .content(ipSaveRequestDto.getContent())
                .siteLink(ipSaveRequestDto.getSiteLink())
                .startDate(ipSaveRequestDto.getStartDate())
                .endDate(ipSaveRequestDto.getEndDate())
                .member(findMember)
                .approval(savedApproval).build();
    }

    private static Cert buildCert(CertSaveRequestDto certSaveRequestDto, System findSystem, Member findMember, Approval savedApproval) {
        return Cert.builder()
                .certType(certSaveRequestDto.getCertType())
                .certName(certSaveRequestDto.getCertName())
                .applySystem(findSystem)
                .content(certSaveRequestDto.getContent())
                .siteLink(certSaveRequestDto.getSiteLink())
                .startDate(certSaveRequestDto.getStartDate())
                .endDate(certSaveRequestDto.getEndDate())
                .member(findMember)
                .approval(savedApproval).build();
    }

}
