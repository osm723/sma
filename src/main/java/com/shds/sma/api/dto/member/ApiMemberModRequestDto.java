package com.shds.sma.api.dto.member;

import com.shds.sma.admin.dto.member.MemberModRequestDto;
import com.shds.sma.admin.entity.Client;
import com.shds.sma.admin.types.EmpAuth;
import com.shds.sma.admin.types.EmpStatus;
import com.shds.sma.admin.types.SystemRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiMemberModRequestDto extends MemberModRequestDto {

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

    private SystemRole systemRole;

    private String validity;

}
