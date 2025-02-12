package com.shds.sma.ip.controller;

import com.shds.sma.admin.dto.member.MemberResponseDto;
import com.shds.sma.admin.dto.system.SystemResponseDto;
import com.shds.sma.common.types.ApprovalStatus;
import com.shds.sma.common.types.Degree;
import com.shds.sma.ip.service.IpService;
import com.shds.sma.ip.dto.IpRequestDto;
import com.shds.sma.ip.dto.IpResponseDto;
import com.shds.sma.ip.types.IpType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ip")
@RequiredArgsConstructor
public class IpController {

    private final IpService ipService;

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
        model.addAttribute("ip", ip);
        setIpModel(model);
        return "/ip/ipManageDetail";
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

        List<MemberResponseDto> members = ipService.findMemberAll();
        model.addAttribute("members", members);

        List<SystemResponseDto> systems = ipService.findSystemAll();
        model.addAttribute("systems", systems);
    }

}
