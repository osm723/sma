package com.shds.sma.common.helper;

import com.shds.sma.apps.admin.client.dto.ClientResponseDto;
import com.shds.sma.apps.admin.client.service.ClientAdminService;
import com.shds.sma.apps.admin.common.service.SystemAdminService;
import com.shds.sma.apps.admin.member.dto.MemberResponseDto;
import com.shds.sma.apps.admin.member.entity.type.EmpAuth;
import com.shds.sma.apps.admin.member.entity.type.EmpStatus;
import com.shds.sma.apps.admin.member.service.MemberAdminService;
import com.shds.sma.apps.system.entity.type.SystemRole;
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

    private final MemberAdminService memberAdminService;

    private final SystemAdminService systemAdminService;

    private final ClientAdminService clientAdminService;

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
        List<MemberResponseDto> members = memberAdminService.findMemberAll();
        model.addAttribute("members", members);
    }

    private void setSystemModel(Model model) {
        List<SystemResponseDto> systems = systemAdminService.findSystemAll();
        model.addAttribute("systems", systems);
    }

    private void setClientModel(Model model) {
        List<ClientResponseDto> clients = clientAdminService.findClientAll();
        model.addAttribute("clients", clients);
    }
}