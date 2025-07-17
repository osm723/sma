package com.shds.sma.apps.ip.controller;

import com.shds.sma.common.helper.ModelHelper;
import com.shds.sma.apps.ip.service.IpService;
import com.shds.sma.apps.ip.dto.IpRequestDto;
import com.shds.sma.apps.ip.dto.IpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ip")
@RequiredArgsConstructor
public class IpController {

    private final IpService ipService;

    private final ModelHelper modelHelper;

    /**
     * IP관리 화면
     * ipManage
     * @return String
     */
    @GetMapping("/manage")
    public String ipManage(IpRequestDto ipRequestDto, Pageable pageable, Model model) {
        Page<IpResponseDto> ips =  ipService.findIpByCond(ipRequestDto, pageable);
        model.addAttribute("ips", ips);
        model.addAttribute("cond", ipRequestDto);
        return "/ip/ipManage";
    }

    /**
     * IP관리 상세화면
     * ipDetail
     * @return String
     */
    @GetMapping("/manage/detail")
    public String ipDetail(Long ipId, Model model) {
        IpResponseDto ip = ipService.findIpById(ipId);
        if (ip.getApproval() != null) {
            ip.setDrafterId(ip.getApproval().getDrafterId());
            ip.setApproverId(ip.getApproval().getApproverId());
        }
        modelHelper.setIpModel(model, ip);
        return "/ip/ipManageDetail";
    }

}
