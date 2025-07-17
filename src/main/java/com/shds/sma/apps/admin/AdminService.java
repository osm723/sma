//package com.shds.sma.apps.admin;
//
//import com.shds.sma.apps.admin.client.dto.ClientModRequestDto;
//import com.shds.sma.apps.admin.client.dto.ClientRequestDto;
//import com.shds.sma.apps.admin.client.dto.ClientResponseDto;
//import com.shds.sma.apps.admin.client.dto.ClientSaveRequestDto;
//import com.shds.sma.apps.admin.notice.dto.*;
//import com.shds.sma.apps.cert.dto.CertModRequestDto;
//import com.shds.sma.apps.cert.dto.CertSaveRequestDto;
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
//import com.shds.sma.apps.admin.member.entity.type.EmpStatus;
//import com.shds.sma.apps.cert.dto.CertRequestDto;
//import com.shds.sma.apps.cert.dto.CertResponseDto;
//import com.shds.sma.apps.ip.dto.IpRequestDto;
//import com.shds.sma.apps.ip.dto.IpResponseDto;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//import java.util.List;
//
//public interface AdminService {
//
//    Page<NoticeResponseDto> findNoticeAll(Pageable pageable);
//
//    Page<NoticeResponseDto> findNoticeByCond(NoticeCondRequestDto noticeCondRequestDto, Pageable pageable);
//
//    Page<NoticeHomeResponseDto> findHomeNotice(Pageable pageable);
//
//    NoticeResponseDto findNoticeById(Long noticeId);
//
//    void saveNotice(NoticeSaveRequestDto noticeSaveRequestDto);
//
//    void modifiedNotice(NoticeModRequestDto noticeModRequestDto);
//
//    void removeNotice(Long noticeId);
//
//    void useNotice(Long noticeId);
//
//    List<SystemResponseDto> findSystemAll();
//
//    Page<SystemResponseDto> findSystemByCond(SystemRequestDto systemRequestDto, Pageable pageable);
//
//    SystemResponseDto findSystemById(Long systemId);
//
//    void systemSave(SystemSaveRequestDto systemSaveRequestDto);
//
//    void modifiedSystem(SystemModRequestDto systemModRequestDto);
//
//    void removeSystem(Long systemId);
//
//    void useSystem(Long systemId);
//
//    List<ClientResponseDto> findClientAll();
//
//    Page<ClientResponseDto> findClientByCond(ClientRequestDto clientRequestDto, Pageable pageable);
//
//    ClientResponseDto findClientById(Long clientId);
//
//    void saveClient(ClientSaveRequestDto clientSaveRequestDto);
//
//    void modifiedClient(ClientModRequestDto clientModRequestDto);
//
//    void removeClient(Long clientId);
//
//    void useClient(Long clientId);
//
//    List<MemberResponseDto> findMemberAll();
//
//    Page<MemberResponseDto> findMemberByCond(MemberRequestDto memberRequestDto, Pageable pageable);
//
//    MemberResponseDto findMemberById(Long memberId);
//
//    void saveMember(MemberSaveRequestDto memberSaveRequestDto);
//
//    void modifiedMember(MemberModRequestDto memberModRequestDto);
//
//    void memberChangeStatus(Long memberId, EmpStatus empStatus);
//
//    Page<IpResponseDto> findIpByCond(IpRequestDto ipRequestDto, Pageable pageable);
//
//    IpResponseDto findIpById(Long ipId);
//
//    void saveIp(IpSaveRequestDto ipSaveRequestDto);
//
//    void modifiedIp(IpModRequestDto ipModRequestDto);
//
//    void removeIp(Long ipId);
//
//    void useIp(Long ipId);
//
//    Page<CertResponseDto> findCertByCond(CertRequestDto certRequestDto, Pageable pageable);
//
//    CertResponseDto findCertById(Long certId);
//
//    void saveCert(CertSaveRequestDto certSaveRequestDto);
//
//    void modifiedCert(CertModRequestDto certModRequestDto);
//
//    void removeCert(Long certId);
//
//    void useCert(Long certId);
//}
