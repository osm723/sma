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
public class MemberModRequestDto {

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

}
