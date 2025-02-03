package com.shds.sma.admin.dto.member;

import com.shds.sma.admin.entity.Client;
import com.shds.sma.admin.entity.Member;
import com.shds.sma.admin.entity.System;
import com.shds.sma.admin.entity.types.EmpAuth;
import com.shds.sma.admin.entity.types.SystemRole;
import com.shds.sma.admin.entity.types.EmpStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberResponseDto {

    private Long memberId;

    private String name;

    private Client client;

    private String deptCode;

    private String deptName;

    private String gradeCode;

    private String gradeName;

    private String roleCode;

    private String roleName;

    private EmpStatus empStatus;

    private EmpAuth empAuth;

    private System system;

    private SystemRole systemRole;

    private LocalDateTime modDate;

    private Long modMemberId;

    private String validity;

    public MemberResponseDto(Member member) {
        this.memberId = member.getId();
        this.name = member.getName();
        this.client = member.getClient();
        this.deptCode = member.getDeptCode();
        this.deptName = member.getDeptName();
        this.gradeCode = member.getGradeCode();
        this.gradeName = member.getGradeName();
        this.roleCode = member.getRoleCode();
        this.roleName = member.getRoleName();
        this.empStatus = member.getEmpStatus();
        this.empAuth = member.getEmpAuth();
        this.system = member.getSystem();
        this.systemRole = member.getSystemRole();
    }
}
