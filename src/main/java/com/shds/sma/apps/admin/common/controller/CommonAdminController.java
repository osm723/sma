package com.shds.sma.apps.admin.common.controller;

import com.shds.sma.apps.admin.common.service.CertAdminService;
import com.shds.sma.apps.admin.common.service.IpAdminService;
import com.shds.sma.apps.admin.common.service.SystemAdminService;
import com.shds.sma.apps.admin.member.dto.MemberResponseDto;
import com.shds.sma.apps.admin.member.service.MemberAdminService;
import com.shds.sma.apps.cert.dto.CertModRequestDto;
import com.shds.sma.apps.cert.dto.CertRequestDto;
import com.shds.sma.apps.cert.dto.CertResponseDto;
import com.shds.sma.apps.cert.dto.CertSaveRequestDto;
import com.shds.sma.apps.ip.dto.IpModRequestDto;
import com.shds.sma.apps.ip.dto.IpRequestDto;
import com.shds.sma.apps.ip.dto.IpResponseDto;
import com.shds.sma.apps.ip.dto.IpSaveRequestDto;
import com.shds.sma.apps.system.dto.SystemModRequestDto;
import com.shds.sma.apps.system.dto.SystemRequestDto;
import com.shds.sma.apps.system.dto.SystemResponseDto;
import com.shds.sma.apps.system.dto.SystemSaveRequestDto;
import com.shds.sma.common.helper.ModelHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.shds.sma.common.constants.Constants.*;
import static com.shds.sma.common.constants.Constants.CERT_UNUSE_SUCCESS;
import static com.shds.sma.common.constants.Constants.CERT_USE_SUCCESS;
import static com.shds.sma.common.constants.Constants.IP_USE_SUCCESS;
import static com.shds.sma.common.constants.Constants.UrlPath.ADMIN_URL;

@Controller
@RequestMapping(ADMIN_URL)
@RequiredArgsConstructor
public class CommonAdminController {

    private final SystemAdminService systemAdminService;

    private final CertAdminService certAdminService;

    private final IpAdminService ipAdminService;

    private final MemberAdminService memberAdminService;

    private final ModelHelper modelHelper;

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
     * 시스템 조회화면 (조건)
     * system
     * @param pageable
     * @param model
     * @return String
     */
    @GetMapping("/system")
    public String system(SystemRequestDto systemRequestDto, Pageable pageable, Model model) {
        Page<SystemResponseDto> systems = systemAdminService.findSystemByCond(systemRequestDto, pageable);
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
        SystemResponseDto system = systemAdminService.findSystemById(systemId);
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
        List<MemberResponseDto> members = memberAdminService.findMemberAll();
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
        systemAdminService.systemSave(systemSaveRequestDto);
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
        systemAdminService.modifiedSystem(systemModRequestDto);
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
        systemAdminService.removeSystem(systemId);
        return ResponseEntity.ok(SYSTEM_DELETE_SUCCESS);
    }

    /**
     * 시스템 사용
     * systemUse
     * @param systemId
     * @return ResponseEntity<String>
     */
    @PostMapping("/system/use")
    public ResponseEntity<String> systemUse(@RequestParam Long systemId) {
        systemAdminService.useSystem(systemId);
        return ResponseEntity.ok(SYSTEM_USE_SUCCESS);
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
        Page<IpResponseDto> ips = ipAdminService.findIpByCond(ipRequestDto, pageable);
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
        IpResponseDto ip = ipAdminService.findIpById(ipId);
        if (ip.getApproval() != null) {
            ip.setDrafterId(ip.getApproval().getDrafterId());
            ip.setApproverId(ip.getApproval().getApproverId());
        }
        modelHelper.setIpModel(model, ip);
        return "/admin/ip/ipDetail";
    }

    /**
     * ip 등록화면 폼
     * ipSaveForm
     * @return String
     */
    @GetMapping("/ip/save")
    public String ipSaveForm(Model model) {
        modelHelper.setIpModel(model, new IpSaveRequestDto());
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
        ipAdminService.saveIp(ipSaveRequestDto);
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
        ipAdminService.modifiedIp(ipModRequestDto);
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
        ipAdminService.removeIp(ipId);
        return ResponseEntity.ok(IP_UNUSE_SUCCESS);
    }

    /**
     * ip 사용
     * ipUse
     * @param ipId
     * @return ResponseEntity<String>
     */
    @PostMapping("/ip/use")
    public ResponseEntity<String> ipUse(@RequestParam Long ipId) {
        ipAdminService.useIp(ipId);
        return ResponseEntity.ok(IP_USE_SUCCESS);
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
        Page<CertResponseDto> certs = certAdminService.findCertByCond(certRequestDto, pageable);
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
        CertResponseDto cert = certAdminService.findCertById(certId);

        if (cert.getApproval() != null) {
            cert.setDrafterId(cert.getApproval().getDrafterId());
            cert.setApproverId(cert.getApproval().getApproverId());
        }
        modelHelper.setCertModel(model, cert);
        return "/admin/cert/certDetail";
    }

    /**
     * 인증서 등록화면 폼
     * certSaveForm
     * @return String
     */
    @GetMapping("/cert/save")
    public String certSaveForm(Model model) {
        modelHelper.setCertModel(model, new CertSaveRequestDto());
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
        certAdminService.saveCert(certSaveRequestDto);
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
        certAdminService.modifiedCert(certModRequestDto);
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
        certAdminService.removeCert(certId);
        return ResponseEntity.ok(CERT_UNUSE_SUCCESS);
    }

    /**
     * 인증서 사용
     * certUse
     * @param certId
     * @return ResponseEntity<String>
     */
    @PostMapping("/cert/use")
    public ResponseEntity<String> certUse(@RequestParam Long certId) {
        certAdminService.useCert(certId);
        return ResponseEntity.ok(CERT_USE_SUCCESS);
    }
}
