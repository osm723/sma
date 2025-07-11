package com.shds.sma.admin.dto.member;

import com.shds.sma.admin.entity.Client;
import com.shds.sma.system.entity.System;
import com.shds.sma.admin.types.EmpAuth;
import com.shds.sma.admin.types.SystemRole;
import com.shds.sma.admin.types.EmpStatus;
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
