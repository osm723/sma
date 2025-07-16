package com.shds.sma.apps.admin.helper;

import com.shds.sma.apps.admin.dto.client.ClientResponseDto;
import com.shds.sma.apps.admin.dto.member.MemberResponseDto;
import com.shds.sma.apps.admin.service.AdminService;
import com.shds.sma.apps.admin.types.EmpAuth;
import com.shds.sma.apps.admin.types.EmpStatus;
import com.shds.sma.apps.admin.types.SystemRole;
import com.shds.sma.apps.cert.types.CertType;
import com.shds.sma.apps.ip.types.IpType;
import com.shds.sma.common.types.ApprovalStatus;
import com.shds.sma.common.types.Degree;
import com.shds.sma.apps.system.dto.SystemResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminModelHelper {

    private final AdminService adminService;

    public void setMemberModel(Model model) {
        model.addAttribute("empStatuses", List.of(EmpStatus.values()));
        model.addAttribute("empAuths", List.of(EmpAuth.values()));
        model.addAttribute("systemRoles", List.of(SystemRole.values()));

        List<SystemResponseDto> systems = adminService.findSystemAll();
        model.addAttribute("systems", systems);

        List<ClientResponseDto> clients = adminService.findClientAll();
        model.addAttribute("clients", clients);
    }

    public void setIpModel(Model model) {
        model.addAttribute("ipTypes", List.of(IpType.values()));
        model.addAttribute("degrees", List.of(Degree.values()));
        model.addAttribute("approvalStatuses", List.of(ApprovalStatus.values()));
        setMemberAndSystemModel(model);
    }

    public void setCertModel(Model model) {
        model.addAttribute("certTypes", List.of(CertType.values()));
        model.addAttribute("degrees", List.of(Degree.values()));
        model.addAttribute("approvalStatuses", List.of(ApprovalStatus.values()));
        setMemberAndSystemModel(model);
    }

    private void setMemberAndSystemModel(Model model) {
        List<MemberResponseDto> members = adminService.findMemberAll();
        model.addAttribute("members", members);

        List<SystemResponseDto> systems = adminService.findSystemAll();
        model.addAttribute("systems", systems);
    }
}