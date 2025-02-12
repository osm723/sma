package com.shds.sma.admin.controller;

import com.shds.sma.admin.dto.client.ClientModRequestDto;
import com.shds.sma.admin.dto.client.ClientRequestDto;
import com.shds.sma.admin.dto.client.ClientResponseDto;
import com.shds.sma.admin.dto.client.ClientSaveRequestDto;
import com.shds.sma.cert.dto.CertModRequestDto;
import com.shds.sma.cert.dto.CertSaveRequestDto;
import com.shds.sma.common.types.ApprovalStatus;
import com.shds.sma.common.types.Degree;
import com.shds.sma.ip.dto.IpModRequestDto;
import com.shds.sma.admin.dto.member.MemberModRequestDto;
import com.shds.sma.admin.dto.member.MemberRequestDto;
import com.shds.sma.admin.dto.member.MemberResponseDto;
import com.shds.sma.admin.dto.member.MemberSaveRequestDto;
import com.shds.sma.admin.dto.notice.NoticeCondRequestDto;
import com.shds.sma.admin.dto.notice.NoticeModRequestDto;
import com.shds.sma.admin.dto.notice.NoticeResponseDto;
import com.shds.sma.admin.dto.notice.NoticeSaveRequestDto;
import com.shds.sma.admin.dto.system.SystemModRequestDto;
import com.shds.sma.admin.dto.system.SystemRequestDto;
import com.shds.sma.admin.dto.system.SystemResponseDto;
import com.shds.sma.admin.dto.system.SystemSaveRequestDto;
import com.shds.sma.admin.types.EmpAuth;
import com.shds.sma.admin.types.SystemRole;
import com.shds.sma.admin.types.EmpStatus;
import com.shds.sma.admin.service.AdminService;
import com.shds.sma.cert.dto.CertRequestDto;
import com.shds.sma.cert.dto.CertResponseDto;
import com.shds.sma.ip.dto.IpRequestDto;
import com.shds.sma.ip.dto.IpResponseDto;
import com.shds.sma.ip.dto.IpSaveRequestDto;
import com.shds.sma.cert.types.CertType;
import com.shds.sma.ip.types.IpType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService adminService;

    /**
     * 관리자 메인화면
     * adminMain
     * @return String
     */
    @GetMapping("/main")
    public String adminMain() {
        return "/admin/adminMain";
    }

    /**
     * 공지사항 조회화면 (조건)
     * notice
     * @param pageable
     * @param model
     * @return String
     */
    @GetMapping("/notice")
    public String notice(NoticeCondRequestDto noticeCondRequestDto, Pageable pageable, Model model) {
        //Page<NoticeResponseDto> notices = adminService.findNoticeAll(pageable);
        Page<NoticeResponseDto> notices = adminService.findNoticeByCond(noticeCondRequestDto, pageable);
        model.addAttribute("notices", notices);
        model.addAttribute("cond", noticeCondRequestDto);
        return "/admin/notice/noticeMain";
    }

    /**
     * 공지사항 상세화면
     * noticeDetail
     * @param noticeId
     * @param model
     * @return String
     */
    @GetMapping("/notice/detail")
    public String noticeDetail(Long noticeId, Model model) {
        NoticeResponseDto notice = adminService.findNoticeById(noticeId);
        model.addAttribute("notice", notice);
        return "/admin/notice/noticeDetail";
    }

    /**
     * 공지사항 등록화면 폼
     * noticeSaveForm
     * @return String
     */
    @GetMapping("/notice/save")
    public String noticeSaveForm() {
        return "/admin/notice/noticeSaveForm";
    }

    /**
     * 공지사항 저장
     * noticeSave
     * @param noticeSaveRequestDto
     * @return String
     */
    @PostMapping("/notice/save")
    public String noticeSave(NoticeSaveRequestDto noticeSaveRequestDto) {
        adminService.saveNotice(noticeSaveRequestDto);
        return "redirect:/admin/notice";
    }

    /**
     * 공지사항 수정
     * modifiedNotice
     * @param noticeModRequestDto
     * @return String
     */
    @PostMapping("/notice/modified")
    public String noticeModified(NoticeModRequestDto noticeModRequestDto) {
        adminService.modifiedNotice(noticeModRequestDto);
        return "redirect:/admin/notice/detail?noticeId="+noticeModRequestDto.getNoticeId();
    }

    /**
     * 공지사항 삭제
     * noticeRemove
     * @param noticeId
     * @return ResponseEntity<String>
     */
    @PostMapping("/notice/remove")
    public ResponseEntity<String> noticeRemove(@RequestParam Long noticeId) {
        adminService.removeNotice(noticeId);
        return ResponseEntity.ok("공지를 삭제 완료했습니다.");
    }

    /**
     * 공지사항 사용
     * noticeUse
     * @param noticeId
     * @return ResponseEntity<String>
     */
    @PostMapping("/notice/use")
    public ResponseEntity<String> noticeUse(@RequestParam Long noticeId) {
        adminService.useNotice(noticeId);
        return ResponseEntity.ok("공지를 사용 완료했습니다.");
    }

    /**
     * 시스템 조회화면 (조건)
     * system
     * @param pageable
     * @param model
     * @return String
     */
    @GetMapping("/system")
    public String system(SystemRequestDto systemRequestDto, Pageable pageable, Model model) {
        Page<SystemResponseDto> systems = adminService.findSystemByCond(systemRequestDto, pageable);
        model.addAttribute("systems", systems);
        model.addAttribute("cond", systemRequestDto);
        return "/admin/system/systemMain";
    }

    /**
     * 시스템 상세화면
     * systemDetail
     * @param systemId
     * @param model
     * @return String
     */
    @GetMapping("/system/detail")
    public String systemDetail(Long systemId, Model model) {
        SystemResponseDto system = adminService.findSystemById(systemId);
        model.addAttribute("system", system);
        return "/admin/system/systemDetail";
    }

    /**
     * 시스템 등록화면 폼
     * systemSaveForm
     * @return String
     */
    @GetMapping("/system/save")
    public String systemSaveForm(Model model) {
        List<MemberResponseDto> members = adminService.findMemberAll();
        model.addAttribute("system", new SystemSaveRequestDto());
        model.addAttribute("members", members);

        return "/admin/system/systemSaveForm";
    }

    /**
     * 시스템 저장
     * systemSave
     * @param systemSaveRequestDto
     * @return String
     */
    @PostMapping("/system/save")
    public String clientSave(SystemSaveRequestDto systemSaveRequestDto) {
        adminService.systemSave(systemSaveRequestDto);
        return "redirect:/admin/system";
    }

    /**
     * 시스템 수정
     * systemModified
     * @param systemModRequestDto
     * @return String
     */
    @PostMapping("/system/modified")
    public String systemModified(SystemModRequestDto systemModRequestDto) {
        adminService.modifiedSystem(systemModRequestDto);
        return "redirect:/admin/system/detail?systemId="+systemModRequestDto.getSystemId();
    }

    /**
     * 시스템 삭제
     * systemRemove
     * @param systemId
     * @return ResponseEntity<String>
     */
    @PostMapping("/system/remove")
    public ResponseEntity<String> systemRemove(@RequestParam Long systemId) {
        adminService.removeSystem(systemId);
        return ResponseEntity.ok("시스템 삭제 완료했습니다.");
    }

    /**
     * 시스템 사용
     * systemUse
     * @param systemId
     * @return ResponseEntity<String>
     */
    @PostMapping("/system/use")
    public ResponseEntity<String> systemUse(@RequestParam Long systemId) {
        adminService.useSystem(systemId);
        return ResponseEntity.ok("시스템 사용 완료했습니다.");
    }

    /**
     * 그룹사 조회화면 (조건)
     * client
     * @param pageable
     * @param model
     * @return String
     */
    @GetMapping("/client")
    public String client(ClientRequestDto clientRequestDto, Pageable pageable, Model model) {
        Page<ClientResponseDto> clients = adminService.findClientByCond(clientRequestDto, pageable);
        model.addAttribute("clients", clients);
        model.addAttribute("cond", clientRequestDto);
        return "/admin/client/clientMain";
    }

    /**
     * 그룹사 상세화면
     * clientDetail
     * @param clientId
     * @param model
     * @return String
     */
    @GetMapping("/client/detail")
    public String clientDetail(Long clientId, Model model) {
        ClientResponseDto client = adminService.findClientById(clientId);
        model.addAttribute("client", client);
        return "/admin/client/clientDetail";
    }

    /**
     * 그룹사 등록화면 폼
     * clientSaveForm
     * @return String
     */
    @GetMapping("/client/save")
    public String clientSaveForm() {
        return "/admin/client/clientSaveForm";
    }

    /**
     * 그룹사 저장
     * clientSave
     * @param clientSaveRequestDto
     * @return String
     */
    @PostMapping("/client/save")
    public String clientSave(ClientSaveRequestDto clientSaveRequestDto) {
        adminService.saveClient(clientSaveRequestDto);
        return "redirect:/admin/client";
    }

    /**
     * 그룹사 수정
     * clientModified
     * @param clientModRequestDto
     * @return String
     */
    @PostMapping("/client/modified")
    public String clientModified(ClientModRequestDto clientModRequestDto) {
        adminService.modifiedClient(clientModRequestDto);
        return "redirect:/admin/client/detail?clientId="+clientModRequestDto.getClientId();
    }

    /**
     * 그룹사 삭제
     * clientRemove
     * @param clientId
     * @return ResponseEntity<String>
     */
    @PostMapping("/client/remove")
    public ResponseEntity<String> clientRemove(@RequestParam Long clientId) {
        adminService.removeClient(clientId);
        return ResponseEntity.ok("그룹사를 미사용처리 완료했습니다.");
    }

    /**
     * 그룹사 사용
     * clientUse
     * @param clientId
     * @return ResponseEntity<String>
     */
    @PostMapping("/client/use")
    public ResponseEntity<String> clientUse(@RequestParam Long clientId) {
        adminService.useClient(clientId);
        return ResponseEntity.ok("그룹사를 사용처리 완료했습니다.");
    }

    /**
     * 직원 조회화면 (조건)
     * member
     * @param
     * @param pageable
     * @param model
     * @return String
     */
    @GetMapping("/member")
    public String member(MemberRequestDto memberRequestDto, Pageable pageable, Model model) {
        Page<MemberResponseDto> members = adminService.findMemberByCond(memberRequestDto, pageable);
        model.addAttribute("members", members);
        model.addAttribute("cond", memberRequestDto);
        return "/admin/member/memberMain";
    }

    /**
     * 직원 상세화면
     * memberDetail
     * @param memberId
     * @param model
     * @return String
     */
    @GetMapping("/member/detail")
    public String memberDetail(Long memberId, Model model) {
        MemberResponseDto member = adminService.findMemberById(memberId);
        model.addAttribute("member", member);
        setMemberModel(model);
        return "/admin/member/memberDetail";
    }

    /**
     * 직원 등록화면 폼
     * memberSaveForm
     * @return String
     */
    @GetMapping("/member/save")
    public String memberSaveForm(Model model) {
        model.addAttribute("member", new MemberSaveRequestDto());
        setMemberModel(model);
        return "/admin/member/memberSaveForm";
    }

    /**
     * 직원 저장
     * memberSave
     * @param memberSaveRequestDto
     * @return String
     */
    @PostMapping("/member/save")
    public String memberSave(MemberSaveRequestDto memberSaveRequestDto) {
        adminService.saveMember(memberSaveRequestDto);
        return "redirect:/admin/member";
    }

    /**
     * 직원 수정
     * memberModified
     * @param memberModRequestDto
     * @return String
     */
    @PostMapping("/member/modified")
    public String memberModified(MemberModRequestDto memberModRequestDto) {
        adminService.modifiedMember(memberModRequestDto);
        return "redirect:/admin/member/detail?memberId="+memberModRequestDto.getMemberId();
    }

    /**
     * 직원 재직정보 변경
     * memberChangeStatus
     * @param memberId
     * @param empStatus
     * @return ResponseEntity<String>
     */
    @PostMapping("/member/remove")
    public ResponseEntity<String> memberChangeStatus(@RequestParam Long memberId, EmpStatus empStatus) {
        adminService.memberChangeStatus(memberId, empStatus);
        return ResponseEntity.ok("직원 재직정보를 변경 완료했습니다.");
    }

    /**
     * ip 조회화면 (조건)
     * ip
     * @param pageable
     * @param model
     * @return String
     */
    @GetMapping("/ip")
    public String ip(IpRequestDto ipRequestDto, Pageable pageable, Model model) {
        Page<IpResponseDto> ips = adminService.findIpByCond(ipRequestDto, pageable);
        model.addAttribute("ips", ips);
        model.addAttribute("cond", ipRequestDto);
        return "/admin/ip/ipMain";
    }

    /**
     * ip 상세화면
     * ipDetail
     * @param ipId
     * @param model
     * @return String
     */
    @GetMapping("/ip/detail")
    public String ipDetail(Long ipId, Model model) {
        IpResponseDto ip = adminService.findIpById(ipId);
        if (ip.getApproval() != null) {
            ip.setDrafterId(ip.getApproval().getDrafterId());
            ip.setApproverId(ip.getApproval().getApproverId());
        }
        model.addAttribute("ip", ip);
        setIpModel(model);
        return "/admin/ip/ipDetail";
    }

    /**
     * ip 등록화면 폼
     * ipSaveForm
     * @return String
     */
    @GetMapping("/ip/save")
    public String ipSaveForm(Model model) {
        model.addAttribute("ip", new IpSaveRequestDto());
        setIpModel(model);
        return "/admin/ip/ipSaveForm";
    }

    /**
     * ip 저장
     * ipSave
     * @param ipSaveRequestDto
     * @return String
     */
    @PostMapping("/ip/save")
    public String ipSave(IpSaveRequestDto ipSaveRequestDto) {
        adminService.saveIp(ipSaveRequestDto);
        return "redirect:/admin/ip";
    }

    /**
     * ip 수정
     * ipModified
     * @param ipModRequestDto
     * @return String
     */
    @PostMapping("/ip/modified")
    public String ipModified(IpModRequestDto ipModRequestDto) {
        adminService.modifiedIp(ipModRequestDto);
        return "redirect:/admin/ip/detail?ipId="+ipModRequestDto.getIpId();
    }

    /**
     * ip 삭제
     * ipRemove
     * @param ipId
     * @return ResponseEntity<String>
     */
    @PostMapping("/ip/remove")
    public ResponseEntity<String> ipRemove(@RequestParam Long ipId) {
        adminService.removeIp(ipId);
        return ResponseEntity.ok("IP를 미사용처리 완료했습니다.");
    }

    /**
     * ip 사용
     * ipUse
     * @param ipId
     * @return ResponseEntity<String>
     */
    @PostMapping("/ip/use")
    public ResponseEntity<String> ipUse(@RequestParam Long ipId) {
        adminService.useIp(ipId);
        return ResponseEntity.ok("IP를 사용처리 완료했습니다.");
    }

    /**
     * 인증서 조회화면 (조건)
     * cert
     * @param pageable
     * @param model
     * @return String
     */
    @GetMapping("/cert")
    public String cert(CertRequestDto certRequestDto, Pageable pageable, Model model) {
        Page<CertResponseDto> certs = adminService.findCertByCond(certRequestDto, pageable);
        model.addAttribute("certs", certs);
        model.addAttribute("cond", certRequestDto);
        return "/admin/cert/certMain";
    }

    /**
     * 인증서 상세화면
     * certDetail
     * @param certId
     * @param model
     * @return String
     */
    @GetMapping("/cert/detail")
    public String certDetail(Long certId, Model model) {
        CertResponseDto cert = adminService.findCertById(certId);
        model.addAttribute("cert", cert);
        if (cert.getApproval() != null) {
            cert.setDrafterId(cert.getApproval().getDrafterId());
            cert.setApproverId(cert.getApproval().getApproverId());
        }
        setCertModel(model);
        return "/admin/cert/certDetail";
    }

    /**
     * 인증서 등록화면 폼
     * certSaveForm
     * @return String
     */
    @GetMapping("/cert/save")
    public String certSaveForm(Model model) {
        model.addAttribute("cert", new CertSaveRequestDto());
        setCertModel(model);
        return "/admin/cert/certSaveForm";
    }

    /**
     * 인증서 저장
     * certSave
     * @param certSaveRequestDto
     * @return String
     */
    @PostMapping("/cert/save")
    public String certSave(CertSaveRequestDto certSaveRequestDto) {
        adminService.saveCert(certSaveRequestDto);
        return "redirect:/admin/cert";
    }

    /**
     * 인증서 수정
     * certModified
     * @param certModRequestDto
     * @return String
     */
    @PostMapping("/cert/modified")
    public String certModified(CertModRequestDto certModRequestDto) {
        adminService.modifiedCert(certModRequestDto);
        return "redirect:/admin/cert/detail?certId="+certModRequestDto.getCertId();
    }

    /**
     * 인증서 삭제
     * certRemove
     * @param certId
     * @return ResponseEntity<String>
     */
    @PostMapping("/cert/remove")
    public ResponseEntity<String> certRemove(@RequestParam Long certId) {
        adminService.removeCert(certId);
        return ResponseEntity.ok("인증서를 미사용처리 완료했습니다.");
    }

    /**
     * 인증서 사용
     * certUse
     * @param certId
     * @return ResponseEntity<String>
     */
    @PostMapping("/cert/use")
    public ResponseEntity<String> certUse(@RequestParam Long certId) {
        adminService.useCert(certId);
        return ResponseEntity.ok("인증서를 사용처리 완료했습니다.");
    }

    /**
     * 직원 화면 model 설정
     * setMemberModel
     * @param model
     */
    private void setMemberModel(Model model) {
        model.addAttribute("empStatuses", List.of(EmpStatus.values()));
        model.addAttribute("empAuths", List.of(EmpAuth.values()));
        model.addAttribute("systemRoles", List.of(SystemRole.values()));

        List<SystemResponseDto> systems = adminService.findSystemAll();
        model.addAttribute("systems", systems);

        List<ClientResponseDto> clients = adminService.findClientAll();
        model.addAttribute("clients", clients);
    }

    /**
     * IP 화면 model 설정
     * setMemberModel
     * @param model
     */
    private void setIpModel(Model model) {
        model.addAttribute("ipTypes", List.of(IpType.values()));
        model.addAttribute("degrees", List.of(Degree.values()));
        model.addAttribute("approvalStatuses", List.of(ApprovalStatus.values()));
        setMemberAndSystemModel(model);
    }

    /**
     * 인증서 화면 model 설정
     * setMemberModel
     * @param model
     */
    private void setCertModel(Model model) {
        model.addAttribute("certTypes", List.of(CertType.values()));
        model.addAttribute("degrees", List.of(Degree.values()));
        model.addAttribute("approvalStatuses", List.of(ApprovalStatus.values()));
        setMemberAndSystemModel(model);
    }

    /**
     * 직원,시스템 model 설정
     * setMemberAndSystemModel
     * @param model
     */
    private void setMemberAndSystemModel(Model model) {
        List<MemberResponseDto> members = adminService.findMemberAll();
        model.addAttribute("members", members);

        List<SystemResponseDto> systems = adminService.findSystemAll();
        model.addAttribute("systems", systems);
    }

}
