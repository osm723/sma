package com.shds.sma.external.api.dto.common;

import com.shds.sma.apps.admin.member.entity.Member;
import com.shds.sma.apps.admin.member.entity.type.EmpAuth;
import com.shds.sma.apps.admin.member.entity.type.EmpStatus;
import com.shds.sma.apps.system.entity.type.SystemRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiMember {

    private Long memberId;

    private String name;

    private ApiClient client;

    private String deptCode;

    private String deptName;

    private String gradeCode;

    private String gradeName;

    private String roleCode;

    private String roleName;

    private EmpStatus empStatus;

    private EmpAuth empAuth;

    private ApiSystem system;

    private SystemRole systemRole;

    public ApiMember(Member member) {
        if (member != null) {
            this.memberId = member.getId();
            this.name = member.getName();
            this.client = new ApiClient(member.getClient());
            this.deptCode = member.getDeptCode();
            this.deptName = member.getDeptName();
            this.gradeCode = member.getGradeCode();
            this.gradeName = member.getGradeName();
            this.roleCode = member.getRoleCode();
            this.roleName = member.getRoleName();
            this.empStatus = member.getEmpStatus();
            this.empAuth = member.getEmpAuth();
            this.system = new ApiSystem(member.getSystem());
            this.systemRole = member.getSystemRole();
        }
    }
}
