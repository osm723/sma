package com.shds.sma.common.helper;

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
public class ModelHelper {

    private final AdminService adminService;

    public <T> void setMemberModel(Model model, T member) {
        model.addAttribute("member", member);
        model.addAttribute("empStatuses", List.of(EmpStatus.values()));
        model.addAttribute("empAuths", List.of(EmpAuth.values()));
        model.addAttribute("systemRoles", List.of(SystemRole.values()));

        setSystemModel(model);
        setClientModel(model);
    }

    public <T> void setIpModel(Model model, T ip) {
        model.addAttribute("ip", ip);
        model.addAttribute("ipTypes", List.of(IpType.values()));
        model.addAttribute("degrees", List.of(Degree.values()));
        model.addAttribute("approvalStatuses", List.of(ApprovalStatus.values()));

        setMemberModel(model);
        setSystemModel(model);
    }

    public <T> void setCertModel(Model model, T cert) {
        model.addAttribute("cert", cert);
        model.addAttribute("certTypes", List.of(CertType.values()));
        model.addAttribute("degrees", List.of(Degree.values()));
        model.addAttribute("approvalStatuses", List.of(ApprovalStatus.values()));

        setMemberModel(model);
        setSystemModel(model);
    }

    private void setMemberModel(Model model) {
        List<MemberResponseDto> members = adminService.findMemberAll();
        model.addAttribute("members", members);
    }

    private void setSystemModel(Model model) {
        List<SystemResponseDto> systems = adminService.findSystemAll();
        model.addAttribute("systems", systems);
    }

    private void setClientModel(Model model) {
        List<ClientResponseDto> clients = adminService.findClientAll();
        model.addAttribute("clients", clients);
    }
}