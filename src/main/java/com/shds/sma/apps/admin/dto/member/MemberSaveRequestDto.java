package com.shds.sma.apps.admin.dto.member;

import com.shds.sma.apps.admin.entity.Client;
import com.shds.sma.apps.system.entity.System;
import com.shds.sma.apps.admin.types.EmpAuth;
import com.shds.sma.apps.admin.types.SystemRole;
import com.shds.sma.apps.admin.types.EmpStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberSaveRequestDto {

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
