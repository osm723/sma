package com.shds.sma.external.api.dto.member;

import com.shds.sma.apps.admin.member.entity.type.EmpAuth;
import com.shds.sma.apps.admin.member.entity.type.EmpStatus;
import com.shds.sma.apps.system.entity.type.SystemRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiMemberSaveRequestDto {

    private String name;

    private String clientCode;

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

    private SystemRole systemRole;

    private String systemName;

    private String validity;

}
