package com.shds.sma.admin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shds.sma.admin.dto.member.MemberModRequestDto;
import com.shds.sma.admin.types.EmpAuth;
import com.shds.sma.admin.types.EmpStatus;
import com.shds.sma.admin.types.SystemRole;
import com.shds.sma.cert.entity.Cert;
import com.shds.sma.ip.entity.Ip;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "SMA_MEBER")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(length = 40, columnDefinition = "VARCHAR(40) COMMENT '직원명'")
    @NotBlank
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    @NotNull
    private Client client;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '부서코드'")
    private String deptCode;

    @Column(length = 60, columnDefinition = "VARCHAR(60) COMMENT '부서명'")
    private String deptName;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '직위코드'")
    private String gradeCode;

    @Column(length = 60, columnDefinition = "VARCHAR(60) COMMENT '직위명'")
    private String gradeName;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '직책코드'")
    private String roleCode;

    @Column(length = 60, columnDefinition = "VARCHAR(60) COMMENT '직책명'")
    private String roleName;

    @Column(length = 200, columnDefinition = "VARCHAR(200) COMMENT '메일주소'")
    private String mail;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '핸드폰 번호'")
    private String phone;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '재직상태'", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private EmpStatus empStatus;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '시스템 권한'", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private EmpAuth empAuth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SYSTEM_ID")
    private System system;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '담당 시스템 역할'", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private SystemRole systemRole;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Ip> ip;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Cert> cert;

    public void memberModified(MemberModRequestDto memberModRequestDto) {
        this.name = memberModRequestDto.getName();
        this.client = memberModRequestDto.getClient();
        this.deptCode = memberModRequestDto.getDeptCode();
        this.deptName = memberModRequestDto.getDeptName();
        this.gradeCode = memberModRequestDto.getGradeCode();
        this.gradeName = memberModRequestDto.getGradeName();
        this.roleCode = memberModRequestDto.getRoleCode();
        this.roleName = memberModRequestDto.getRoleName();
        this.mail = memberModRequestDto.getMail();
        this.phone = memberModRequestDto.getPhone();
        this.empStatus = memberModRequestDto.getEmpStatus();
        this.empAuth = memberModRequestDto.getEmpAuth();
        this.system = memberModRequestDto.getSystem();
        this.systemRole = memberModRequestDto.getSystemRole();
    }

    public void empStatusChange(EmpStatus empStatus) {
        this.empStatus = empStatus;
    }

    @Builder
    public Member(String name, Client client, String deptCode, String deptName, String gradeCode, String gradeName, String roleCode, String roleName, String mail, String phone, EmpStatus empStatus, EmpAuth empAuth, System system, SystemRole systemRole) {
        this.name = name;
        this.client = client;
        this.deptCode = deptCode;
        this.deptName = deptName;
        this.gradeCode = gradeCode;
        this.gradeName = gradeName;
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.mail = mail;
        this.phone = phone;
        this.empStatus = empStatus;
        this.empAuth = empAuth;
        this.system = system;
        this.systemRole = systemRole;
    }
}
