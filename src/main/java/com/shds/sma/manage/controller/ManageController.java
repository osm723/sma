package com.shds.sma.manage.controller;

import com.shds.sma.manage.dto.cert.CertRequestDto;
import com.shds.sma.manage.dto.cert.CertResponseDto;
import com.shds.sma.manage.service.ManageService;
import com.shds.sma.manage.dto.ip.IpRequestDto;
import com.shds.sma.manage.dto.ip.IpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
@RequiredArgsConstructor
public class ManageController {

    private final ManageService manageService;

    /**
     * IP 메인화면
     * ipMain
     * @return String
     */
    @GetMapping("/ip")
    public String ipMain(IpRequestDto ipRequestDto, Pageable pageable, Model model) {
        Page<IpResponseDto> ips =  manageService.findIpByCond(ipRequestDto, pageable);
        model.addAttribute("ips", ips);
        return "/manage/ipMain";
    }

    /**
     * 인증서 메인화면
     * certMain
     * @return String
     */
    @GetMapping("/cert")
    public String certMain(CertRequestDto certRequestDto, Pageable pageable, Model model) {
        Page<CertResponseDto> certs = manageService.findCertByCond(certRequestDto, pageable);
        model.addAttribute("certs", certs);
        return "/manage/certMain";
    }

}
