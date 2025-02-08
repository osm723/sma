package com.shds.sma.cert.controller;

import com.shds.sma.admin.dto.member.MemberResponseDto;
import com.shds.sma.admin.dto.system.SystemResponseDto;
import com.shds.sma.cert.service.CertService;
import com.shds.sma.cert.dto.CertRequestDto;
import com.shds.sma.cert.dto.CertResponseDto;
import com.shds.sma.cert.types.CertType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cert")
@RequiredArgsConstructor
public class CertController {

    private final CertService certService;

    /**
     * 인증서관리 화면
     * certManage
     * @return String
     */
    @GetMapping("/manage")
    public String certManage(CertRequestDto certRequestDto, Pageable pageable, Model model) {
        Page<CertResponseDto> certs = certService.findCertByCond(certRequestDto, pageable);
        model.addAttribute("certs", certs);
        model.addAttribute("cond", certRequestDto);
        return "/manage/certManage";
    }

    /**
     * 인증서관리 상세화면
     * certDetail
     * @return String
     */
    @GetMapping("/manage/detail")
    public String certDetail(Long certId, Model model) {
        CertResponseDto cert = certService.findCertById(certId);
        model.addAttribute("cert", cert);
        setCertModel(model);
        return "/manage/certManageDetail";
    }

    /**
     * 인증서 화면 model 설정
     * setMemberModel
     * @param model
     */
    private void setCertModel(Model model) {
        model.addAttribute("certTypes", List.of(CertType.values()));

        List<MemberResponseDto> members = certService.findMemberAll();
        model.addAttribute("members", members);

        List<SystemResponseDto> systems = certService.findSystemAll();
        model.addAttribute("systems", systems);
    }

}
