//package com.shds.sma.apps.admin;
//
//import com.shds.sma.apps.admin.client.dto.ClientModRequestDto;
//import com.shds.sma.apps.admin.client.dto.ClientRequestDto;
//import com.shds.sma.apps.admin.client.dto.ClientResponseDto;
//import com.shds.sma.apps.admin.client.dto.ClientSaveRequestDto;
//import com.shds.sma.apps.admin.notice.dto.*;
//import com.shds.sma.apps.admin.common.repository.ApprovalRepository;
//import com.shds.sma.apps.cert.dto.CertModRequestDto;
//import com.shds.sma.apps.cert.dto.CertSaveRequestDto;
//import com.shds.sma.apps.admin.common.entity.Approval;
//import com.shds.sma.common.exception.BizException;
//import com.shds.sma.apps.ip.dto.IpModRequestDto;
//import com.shds.sma.apps.ip.dto.IpSaveRequestDto;
//import com.shds.sma.apps.admin.member.dto.MemberModRequestDto;
//import com.shds.sma.apps.admin.member.dto.MemberRequestDto;
//import com.shds.sma.apps.admin.member.dto.MemberResponseDto;
//import com.shds.sma.apps.admin.member.dto.MemberSaveRequestDto;
//import com.shds.sma.apps.system.dto.SystemModRequestDto;
//import com.shds.sma.apps.system.dto.SystemRequestDto;
//import com.shds.sma.apps.system.dto.SystemResponseDto;
//import com.shds.sma.apps.system.dto.SystemSaveRequestDto;
//import com.shds.sma.apps.admin.client.entity.Client;
//import com.shds.sma.apps.admin.member.entity.Member;
//import com.shds.sma.apps.admin.notice.entity.Notice;
//import com.shds.sma.apps.system.entity.System;
//import com.shds.sma.apps.admin.member.entity.type.EmpStatus;
//import com.shds.sma.apps.admin.client.repository.ClientRepository;
//import com.shds.sma.apps.admin.member.repository.MemberRepository;
//import com.shds.sma.apps.admin.notice.repository.NoticeRepository;
//import com.shds.sma.apps.system.repository.SystemRepository;
//import com.shds.sma.apps.cert.dto.CertRequestDto;
//import com.shds.sma.apps.cert.dto.CertResponseDto;
//import com.shds.sma.apps.ip.dto.IpRequestDto;
//import com.shds.sma.apps.ip.dto.IpResponseDto;
//import com.shds.sma.apps.cert.entity.Cert;
//import com.shds.sma.apps.ip.entity.Ip;
//import com.shds.sma.apps.cert.repository.CertRepository;
//import com.shds.sma.apps.ip.repository.IpRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static com.shds.sma.common.exception.ExceptionMessageConst.*;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//@Slf4j
//public class AdminServiceImpl implements AdminService {
//
//    private final NoticeRepository noticeRepository;
//
//    private final SystemRepository systemRepository;
//
//    private final ClientRepository clientRepository;
//
//    private final MemberRepository memberRepository;
//
//    private final IpRepository ipRepository;
//
//    private final CertRepository certRepository;
//
//    private final ApprovalRepository approvalRepository;
//
//    private final ModelMapper modelMapper;
//
//    @Override
//    @Transactional(readOnly = true)
//    public Page<NoticeResponseDto> findNoticeAll(Pageable pageable) {
//        Page<Notice> notices = noticeRepository.findAll(pageable);
//        return notices.map(NoticeResponseDto::new);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Page<NoticeResponseDto> findNoticeByCond(NoticeCondRequestDto noticeCondRequestDto, Pageable pageable) {
//        Page<Notice> notices = noticeRepository.findNoticeByCond(noticeCondRequestDto, pageable);
//        return notices.map(NoticeResponseDto::new);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Page<NoticeHomeResponseDto> findHomeNotice(Pageable pageable) {
//        Page<Notice> notices = noticeRepository.findHomeNotice(pageable);
//        return notices.map(NoticeHomeResponseDto::new);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public NoticeResponseDto findNoticeById(Long noticeId) {
//        Notice notice = getNotice(noticeId);
//        return modelMapper.map(notice, NoticeResponseDto.class);
//    }
//
//    @Override
//    public void saveNotice(NoticeSaveRequestDto noticeSaveRequestDto) {
//        noticeRepository.save(modelMapper.map(noticeSaveRequestDto, Notice.class));
//    }
//
//    @Override
//    public void modifiedNotice(NoticeModRequestDto noticeModRequestDto) {
//        Notice findNotice = getNotice(noticeModRequestDto.getNoticeId());
//        findNotice.noticeModified(noticeModRequestDto);
//    }
//
//    @Override
//    public void removeNotice(Long noticeId) {
//        Notice findNotice = getNotice(noticeId);
//        findNotice.setValidityN();
//    }
//
//    @Override
//    public void useNotice(Long noticeId) {
//        Notice findNotice = getNotice(noticeId);
//        findNotice.setValidityY();
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<SystemResponseDto> findSystemAll() {
//        List<System> findSystem = systemRepository.findAll();
//        return findSystem.stream().map(SystemResponseDto::new).collect(Collectors.toList());
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Page<SystemResponseDto> findSystemByCond(SystemRequestDto systemRequestDto, Pageable pageable) {
//        Page<System> systems = systemRepository.findSystemByCond(systemRequestDto, pageable);
//        return systems.map(SystemResponseDto::new);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public SystemResponseDto findSystemById(Long systemId) {
//        System findSystem = getSystem(systemId);
//        return modelMapper.map(findSystem, SystemResponseDto.class);
//    }
//
//    @Override
//    public void systemSave(SystemSaveRequestDto systemSaveRequestDto) {
//        systemRepository.save(modelMapper.map(systemSaveRequestDto, System.class));
//    }
//
//    @Override
//    public void modifiedSystem(SystemModRequestDto systemModRequestDto) {
//        System findSystem = getSystem(systemModRequestDto.getSystemId());
//        findSystem.systemModified(systemModRequestDto);
//    }
//
//    @Override
//    public void removeSystem(Long systemId) {
//        System findSystem = getSystem(systemId);
//        findSystem.setValidityN();
//    }
//
//    @Override
//    public void useSystem(Long systemId) {
//        System findSystem = getSystem(systemId);
//        findSystem.setValidityY();
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<ClientResponseDto> findClientAll() {
//        List<Client> findClient = clientRepository.findAll();
//        return findClient.stream().map(ClientResponseDto::new).collect(Collectors.toList());
//    }
//
//    @Override
//    public Page<ClientResponseDto> findClientByCond(ClientRequestDto clientRequestDto, Pageable pageable) {
//        Page<Client> clients = clientRepository.findClientByCond(clientRequestDto, pageable);
//        return clients.map(ClientResponseDto::new);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public ClientResponseDto findClientById(Long clientId) {
//        Client findClient = getClient(clientId);
//        return modelMapper.map(findClient, ClientResponseDto.class);
//    }
//
//    @Override
//    public void saveClient(ClientSaveRequestDto clientSaveRequestDto) {
//        clientRepository.save(modelMapper.map(clientSaveRequestDto, Client.class));
//    }
//
//    @Override
//    public void modifiedClient(ClientModRequestDto clientModRequestDto) {
//        Client findClient = getClient(clientModRequestDto.getClientId());
//        findClient.clientModified(clientModRequestDto);
//    }
//
//    @Override
//    public void removeClient(Long clientId) {
//        Client findClient = getClient(clientId);
//        findClient.setValidityN();
//    }
//
//    @Override
//    public void useClient(Long clientId) {
//        Client findClient = getClient(clientId);
//        findClient.setValidityY();
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<MemberResponseDto> findMemberAll() {
//        List<Member> findMember = memberRepository.findAll();
//        return findMember.stream().map(MemberResponseDto::new).collect(Collectors.toList());
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Page<MemberResponseDto> findMemberByCond(MemberRequestDto memberRequestDto, Pageable pageable) {
//        Page<Member> findMember = memberRepository.findMemberByCond(memberRequestDto, pageable);
//        return findMember.map(MemberResponseDto::new);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public MemberResponseDto findMemberById(Long memberId) {
//        Member member = getMember(memberId);
//        return modelMapper.map(member, MemberResponseDto.class);
//    }
//
//    @Override
//    public void saveMember(MemberSaveRequestDto memberSaveRequestDto) {
//        System findSystem = getSystem(memberSaveRequestDto.getSystemId());
//        memberSaveRequestDto.setSystem(findSystem);
//
//        Client findClient = getClient(memberSaveRequestDto.getClientId());
//        memberSaveRequestDto.setClient(findClient);
//
//        memberRepository.save(buildMember(memberSaveRequestDto, findClient, findSystem));
//    }
//
//    @Override
//    public void modifiedMember(MemberModRequestDto memberModRequestDto) {
//        System findSystem = getSystem(memberModRequestDto.getSystemId());
//        memberModRequestDto.setSystem(findSystem);
//
//        Client findClient = getClient(memberModRequestDto.getClientId());
//        memberModRequestDto.setClient(findClient);
//
//        Member findMember = getMember(memberModRequestDto.getMemberId());
//        findMember.memberModified(memberModRequestDto);
//    }
//
//    @Override
//    public void memberChangeStatus(Long memberId, EmpStatus empStatus) {
//        Member findMember = getMember(memberId);
//        findMember.empStatusChange(empStatus);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Page<IpResponseDto> findIpByCond(IpRequestDto ipRequestDto, Pageable pageable) {
//        Page<Ip> findIp = ipRepository.findIpByCond(ipRequestDto, pageable);
//        return findIp.map(IpResponseDto::new);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public IpResponseDto findIpById(Long ipId) {
//        Ip findIp = getIp(ipId);
//        return modelMapper.map(findIp, IpResponseDto.class);
//    }
//
//    @Override
//    public void saveIp(IpSaveRequestDto ipSaveRequestDto) {
//        Approval savedApproval = new Approval();
//        if (ipSaveRequestDto.useApproval()) {
//            savedApproval = saveIpApproval(ipSaveRequestDto);
//        }
//
//        System findSystem = getSystem(ipSaveRequestDto.getApplySystemId());
//
//        Member findMember = getMember(ipSaveRequestDto.getMemberId());
//
//        ipRepository.save(buildIp(ipSaveRequestDto, findSystem, findMember, savedApproval));
//    }
//
//    @Override
//    public void modifiedIp(IpModRequestDto ipModRequestDto) {
//        if (ipModRequestDto.getApprovalId() != null) {
//            Approval findApproval = getApproval(ipModRequestDto.getApprovalId());
//            findApproval.approvalIpModified(ipModRequestDto);
//            ipModRequestDto.setApproval(findApproval);
//        }
//
//        Ip findIp = getIp(ipModRequestDto.getIpId());
//
//        System findSystem = getSystem(ipModRequestDto.getApplySystemId());
//        ipModRequestDto.setApplySystem(findSystem);
//
//        Member findMember = getMember(ipModRequestDto.getMemberId());
//        ipModRequestDto.setMember(findMember);
//
//        findIp.ipModified(ipModRequestDto);
//    }
//
//    @Override
//    public void removeIp(Long ipId) {
//        Ip findIp = getIp(ipId);
//        findIp.setValidityN();
//    }
//
//    @Override
//    public void useIp(Long ipId) {
//        Ip findIp = getIp(ipId);
//        findIp.setValidityY();
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Page<CertResponseDto> findCertByCond(CertRequestDto certRequestDto, Pageable pageable) {
//        Page<Cert> findCert = certRepository.findCertByCond(certRequestDto, pageable);
//        return findCert.map(CertResponseDto::new);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public CertResponseDto findCertById(Long certId) {
//        Cert findCert = getCert(certId);
//        return modelMapper.map(findCert, CertResponseDto.class);
//    }
//
//    @Override
//    public void saveCert(CertSaveRequestDto certSaveRequestDto) {
//        Approval savedApproval = new Approval();
//        if (certSaveRequestDto.useApproval()) {
//            savedApproval = saveCertApproval(certSaveRequestDto);
//        }
//
//        System findSystem = getSystem(certSaveRequestDto.getApplySystemId());
//        Member findMember = getMember(certSaveRequestDto.getMemberId());
//
//        certRepository.save(buildCert(certSaveRequestDto, findSystem, findMember, savedApproval));
//    }
//
//    @Override
//    public void modifiedCert(CertModRequestDto certModRequestDto) {
//        if (certModRequestDto.getApprovalId() != null) {
//            Approval findApproval = getApproval(certModRequestDto.getApprovalId());
//            findApproval.approvalCertModified(certModRequestDto);
//            certModRequestDto.setApproval(findApproval);
//        }
//
//        Cert findCert = getCert(certModRequestDto.getCertId());
//
//        System findSystem = getSystem(certModRequestDto.getApplySystemId());
//        certModRequestDto.setApplySystem(findSystem);
//
//        Member findMember = getMember(certModRequestDto.getMemberId());
//        certModRequestDto.setMember(findMember);
//
//        findCert.certModified(certModRequestDto);
//    }
//
//    @Override
//    public void removeCert(Long certId) {
//        Cert findCert = getCert(certId);
//        findCert.setValidityN();
//    }
//
//    @Override
//    public void useCert(Long certId) {
//        Cert findCert = getCert(certId);
//        findCert.setValidityY();
//    }
//
//    private System getSystem(Long systemId) {
//        return systemRepository.findById(systemId).orElseThrow(() -> new BizException(NOT_FOUND_SYSTEM));
//    }
//
//    private Client getClient(Long clientId) {
//        return clientRepository.findById(clientId).orElseThrow(() -> new BizException(NOT_FOUND_CLIENT));
//    }
//
//    private Member getMember(Long memberId) {
//        return memberRepository.findById(memberId).orElseThrow(() -> new BizException(NOT_FOUND_MEMBER));
//    }
//
//    private Approval getApproval(Long approvalId) {
//        return approvalRepository.findById(approvalId).orElseThrow(() -> new BizException(NOT_FOUND_APPROVAL));
//    }
//
//    private Cert getCert(Long certId) {
//        return certRepository.findById(certId).orElseThrow(() -> new BizException(NOT_FOUND_CERT));
//    }
//
//    private Ip getIp(Long ipId) {
//        return ipRepository.findById(ipId).orElseThrow(() -> new BizException(NOT_FOUND_IP));
//    }
//
//    private Notice getNotice(Long noticeId) {
//        return noticeRepository.findById(noticeId).orElseThrow(() -> new BizException(NOT_FOUND_NOTICE));
//    }
//
//    private static Member buildMember(MemberSaveRequestDto memberSaveRequestDto, Client findClient, System findSystem) {
//        return Member.builder()
//                .name(memberSaveRequestDto.getName())
//                .client(findClient)
//                .deptCode(memberSaveRequestDto.getDeptCode())
//                .deptName(memberSaveRequestDto.getDeptName())
//                .gradeCode(memberSaveRequestDto.getGradeCode())
//                .gradeName(memberSaveRequestDto.getGradeName())
//                .roleCode(memberSaveRequestDto.getRoleCode())
//                .roleName(memberSaveRequestDto.getRoleName())
//                .mail(memberSaveRequestDto.getMail())
//                .phone(memberSaveRequestDto.getPhone())
//                .empStatus(memberSaveRequestDto.getEmpStatus())
//                .empAuth(memberSaveRequestDto.getEmpAuth())
//                .system(findSystem)
//                .systemRole(memberSaveRequestDto.getSystemRole()).build();
//    }
//
//    private Approval saveIpApproval(IpSaveRequestDto ipSaveRequestDto) {
//        Approval saveApproval = Approval.builder()
//                .approvalNo(ipSaveRequestDto.getApprovalNo())
//                .drafterId(ipSaveRequestDto.getDrafterId())
//                .degree(ipSaveRequestDto.getDegree())
//                .approverId(ipSaveRequestDto.getApproverId())
//                .approvalStatus(ipSaveRequestDto.getApprovalStatus())
//                .approveDate(ipSaveRequestDto.getApproveDate())
//                .cancelDate(ipSaveRequestDto.getCancelDate()).build();
//
//        return approvalRepository.save(saveApproval);
//    }
//
//    private Approval saveCertApproval(CertSaveRequestDto certSaveRequestDto) {
//        Approval saveApproval = Approval.builder()
//                .approvalNo(certSaveRequestDto.getApprovalNo())
//                .drafterId(certSaveRequestDto.getDrafterId())
//                .degree(certSaveRequestDto.getDegree())
//                .approverId(certSaveRequestDto.getApproverId())
//                .approvalStatus(certSaveRequestDto.getApprovalStatus())
//                .approveDate(certSaveRequestDto.getApproveDate())
//                .cancelDate(certSaveRequestDto.getCancelDate()).build();
//
//        return approvalRepository.save(saveApproval);
//    }
//
//    private static Ip buildIp(IpSaveRequestDto ipSaveRequestDto, System findSystem, Member findMember, Approval savedApproval) {
//        return Ip.builder()
//                .ipType(ipSaveRequestDto.getIpType())
//                .startIpAddr(ipSaveRequestDto.getStartIpAddr())
//                .endIpAddr(ipSaveRequestDto.getEndIpAddr())
//                .port(ipSaveRequestDto.getPort())
//                .applySystem(findSystem)
//                .content(ipSaveRequestDto.getContent())
//                .siteLink(ipSaveRequestDto.getSiteLink())
//                .startDate(ipSaveRequestDto.getStartDate())
//                .endDate(ipSaveRequestDto.getEndDate())
//                .member(findMember)
//                .approval(savedApproval).build();
//    }
//
//    private static Cert buildCert(CertSaveRequestDto certSaveRequestDto, System findSystem, Member findMember, Approval savedApproval) {
//        return Cert.builder()
//                .certType(certSaveRequestDto.getCertType())
//                .certName(certSaveRequestDto.getCertName())
//                .applySystem(findSystem)
//                .content(certSaveRequestDto.getContent())
//                .siteLink(certSaveRequestDto.getSiteLink())
//                .startDate(certSaveRequestDto.getStartDate())
//                .endDate(certSaveRequestDto.getEndDate())
//                .member(findMember)
//                .approval(savedApproval).build();
//    }
//
//}
