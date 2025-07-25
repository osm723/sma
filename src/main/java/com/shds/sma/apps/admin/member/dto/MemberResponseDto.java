package com.shds.sma.apps.admin.member.dto;

import com.shds.sma.apps.admin.client.entity.Client;
import com.shds.sma.apps.admin.member.entity.Member;
import com.shds.sma.apps.system.entity.System;
import com.shds.sma.apps.admin.member.entity.type.EmpAuth;
import com.shds.sma.apps.system.entity.type.SystemRole;
import com.shds.sma.apps.admin.member.entity.type.EmpStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {

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

    private String mail;

    private String phone;

    private EmpStatus empStatus;

    private EmpAuth empAuth;

    private Long systemId;

    private System system;

    private SystemRole systemRole;


    public MemberResponseDto(Member member) {
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
        this.mail = member.getMail();
        this.phone = member.getPhone();
        this.empStatus = member.getEmpStatus();
        this.empAuth = member.getEmpAuth();
        this.systemId = member.getSystem().getId();
        this.system = member.getSystem();
        this.systemRole = member.getSystemRole();
    }
}
