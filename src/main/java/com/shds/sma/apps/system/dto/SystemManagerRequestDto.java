package com.shds.sma.apps.system.dto;

import com.shds.sma.apps.admin.member.dto.MemberRequestDto;
import com.shds.sma.apps.admin.client.entity.Client;
import com.shds.sma.apps.system.entity.System;
import com.shds.sma.apps.admin.member.entity.type.EmpAuth;
import com.shds.sma.apps.admin.member.entity.type.EmpStatus;
import com.shds.sma.apps.system.entity.type.SystemRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemManagerRequestDto extends MemberRequestDto {

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

    private SystemRole systemRole;

    private String validity;

    private Long systemId;

    private System system;

}
