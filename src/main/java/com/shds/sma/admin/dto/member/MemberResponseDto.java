package com.shds.sma.admin.dto.member;

import com.shds.sma.admin.entity.Client;
import com.shds.sma.admin.entity.System;
import com.shds.sma.admin.entity.types.Auth;
import com.shds.sma.admin.entity.types.EmpStatus;
import com.shds.sma.admin.entity.types.SystemRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberResponseDto {

    private Long id;

    private String name;

    private Client client;

    private String deptCode;

    private String deptName;

    private String gradeCode;

    private String gradeName;

    private String roleCode;

    private String roleName;

    private EmpStatus empStatue;

    private Auth auth;

    private System system;

    private SystemRole systemRole;

    private LocalDateTime modDate;

    private Long modMemberId;

    private String validity;

}
