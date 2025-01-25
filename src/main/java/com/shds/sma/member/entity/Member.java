package com.shds.sma.member.entity;

import com.shds.sma.member.entity.types.SystemRole;
import com.shds.sma.member.entity.types.EmpStatus;
import com.shds.sma.member.entity.types.Auth;
import com.shds.sma.admin.entity.System;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "SMA_MEBER")
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(length = 40, columnDefinition = "VARCHAR(40) COMMENT '직원명'")
    @NotBlank
    private String name;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    @NotNull
    private Client client;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '부서코드'")
    private String deptCd;

    @Column(length = 60, columnDefinition = "VARCHAR(60) COMMENT '부서명'")
    private String deptName;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '직위코드'")
    private String gradeCd;

    @Column(length = 60, columnDefinition = "VARCHAR(60) COMMENT '직위명'")
    private String gradeName;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '직책코드'")
    private String roleCd;

    @Column(length = 60, columnDefinition = "VARCHAR(60) COMMENT '직책명'")
    private String roleName;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '재직상태'", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private EmpStatus empStatue;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '시스템 권한'", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Auth auth;

    @ManyToOne
    @JoinColumn(name = "SYSTEM_ID")
    private System system;

    private SystemRole systemRole;

}
