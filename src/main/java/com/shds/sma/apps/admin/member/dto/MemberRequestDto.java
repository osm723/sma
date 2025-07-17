package com.shds.sma.apps.admin.member.dto;

import com.shds.sma.apps.admin.client.entity.Client;
import com.shds.sma.apps.system.entity.System;
import com.shds.sma.apps.admin.member.entity.type.EmpAuth;
import com.shds.sma.apps.system.entity.type.SystemRole;
import com.shds.sma.apps.admin.member.entity.type.EmpStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequestDto {

    private String name;

    private Client client;

    private String deptCode;

    private String deptName;

    private String gradeCode;

    private String gradeName;

    private String roleCode;

    private String roleName;

    private String mail;

    private EmpStatus empStatus;

    private EmpAuth empAuth;

    private SystemRole systemRole;

    private System system;

    private String validity;

}
