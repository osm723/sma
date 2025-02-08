package com.shds.sma.system.dto;

import com.shds.sma.admin.entity.Client;
import com.shds.sma.admin.entity.Member;
import com.shds.sma.admin.entity.System;
import com.shds.sma.admin.types.EmpAuth;
import com.shds.sma.admin.types.EmpStatus;
import com.shds.sma.admin.types.SystemRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SystemManagerResponseDto {

    private Long memberId;

    private String name;

    private Long clientId;

    private Client client;

    private String deptCode;

    private String deptName;

    private String gradeCode;

    private String gradeName;

    private String roleCode;

    private String roleName;

    private EmpStatus empStatus;

    private EmpAuth empAuth;

    private Long systemId;

    private System system;

    private SystemRole systemRole;


    public SystemManagerResponseDto(Member member) {
        this.memberId = member.getId();
        this.name = member.getName();
        this.clientId = member.getClient().getId();
        this.client = member.getClient();
        this.deptCode = member.getDeptCode();
        this.deptName = member.getDeptName();
        this.gradeCode = member.getGradeCode();
        this.gradeName = member.getGradeName();
        this.roleCode = member.getRoleCode();
        this.roleName = member.getRoleName();
        this.empStatus = member.getEmpStatus();
        this.empAuth = member.getEmpAuth();
        this.systemId = member.getSystem().getId();
        this.system = member.getSystem();
        this.systemRole = member.getSystemRole();
    }
}
