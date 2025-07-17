package com.shds.sma.external.api.dto.member;

import com.shds.sma.apps.admin.member.entity.Member;
import com.shds.sma.apps.admin.member.entity.type.EmpAuth;
import com.shds.sma.apps.admin.member.entity.type.EmpStatus;
import com.shds.sma.apps.system.entity.type.SystemRole;
import com.shds.sma.external.api.dto.common.ApiClient;
import com.shds.sma.external.api.dto.common.ApiSystem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiMemberResponseDto {

    private Long memberId;

    private String name;

    //private Long clientId;

    //private Client client;

    private ApiClient client;

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

    //private Long systemId;

    //private System system;

    private ApiSystem system;

    private SystemRole systemRole;


    public ApiMemberResponseDto(Member member) {
        this.memberId = member.getId();
        this.name = member.getName();
        //this.clientId = member.getClient().getId();
        this.client = new ApiClient(member.getClient());
        this.deptCode = member.getDeptCode();
        this.deptName = member.getDeptName();
        this.gradeCode = member.getGradeCode();
        this.gradeName = member.getGradeName();
        this.roleCode = member.getRoleCode();
        this.roleName = member.getRoleName();
        this.mail = member.getMail();
        this.phone = member.getPhone();
        this.empStatus = member.getEmpStatus();
        this.empAuth = member.getEmpAuth();
        //this.systemId = member.getSystem().getId();
        this.system = new ApiSystem(member.getSystem());
        this.systemRole = member.getSystemRole();
    }
}
