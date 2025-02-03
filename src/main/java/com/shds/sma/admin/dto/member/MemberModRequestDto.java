package com.shds.sma.admin.dto.member;

import com.shds.sma.admin.entity.Client;
import com.shds.sma.admin.entity.System;
import com.shds.sma.admin.entity.types.EmpAuth;
import com.shds.sma.admin.entity.types.SystemAuth;
import com.shds.sma.admin.entity.types.EmpStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberModRequestDto {

    private Long memberId;

    private String name;

    private Client client;

    private String deptCode;

    private String deptName;

    private String gradeCode;

    private String gradeName;

    private String roleCode;

    private String roleName;

    private EmpStatus empStatue;

    private EmpAuth empAuth;

    private System system;

    private SystemAuth systemAuth;

}
