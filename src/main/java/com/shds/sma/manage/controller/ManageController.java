package com.shds.sma.manage.controller;

import com.shds.sma.admin.dto.member.MemberResponseDto;
import com.shds.sma.admin.dto.system.SystemResponseDto;
import com.shds.sma.manage.dto.cert.CertRequestDto;
import com.shds.sma.manage.dto.cert.CertResponseDto;
import com.shds.sma.manage.service.ManageService;
import com.shds.sma.manage.dto.ip.IpRequestDto;
import com.shds.sma.manage.dto.ip.IpResponseDto;
import com.shds.sma.manage.types.IpType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manage")
@RequiredArgsConstructor
public class ManageController {

    private final ManageService manageService;

    /**
     * IP관리 화면
     * ipManage
     * @return String
     */
    @GetMapping("/ipManage")
    public String ipManage(IpRequestDto ipRequestDto, Pageable pageable, Model model) {
        Page<IpResponseDto> ips =  manageService.findIpByCond(ipRequestDto, pageable);
        model.addAttribute("ips", ips);
        model.addAttribute("cond", ipRequestDto);
        return "/manage/ipManage";
    }

    /**
     * IP관리 상세화면
     * ipManageDetail
     * @return String
     */
    @GetMapping("/ipManage/detail")
    public String ipManageDetail(Long ipId, Model model) {
        IpResponseDto ip = manageService.findIpById(ipId);
        model.addAttribute("ip", ip);
        setIpModel(model);
        return "/manage/ipManageDetail";
    }

    /**
     * 인증서관리 화면
     * certManage
     * @return String
     */
    @GetMapping("/certManage")
    public String certManage(CertRequestDto certRequestDto, Pageable pageable, Model model) {
        Page<CertResponseDto> certs = manageService.findCertByCond(certRequestDto, pageable);
        model.addAttribute("certs", certs);
        return "/manage/certManage";
    }

    /**
     * 직원 화면 model 설정
     * setMemberModel
     * @param model
     */
    private void setIpModel(Model model) {
        model.addAttribute("ipTypes", List.of(IpType.values()));

        List<MemberResponseDto> members = manageService.findMemberAll();
        model.addAttribute("members", members);

        List<SystemResponseDto> systems = manageService.findSystemAll();
        model.addAttribute("systems", systems);
    }

}
