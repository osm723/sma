package com.shds.sma.manage.entity;

import com.shds.sma.admin.entity.Member;
import com.shds.sma.common.entity.BaseEntity;
import com.shds.sma.admin.entity.System;
import com.shds.sma.manage.types.CertType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "SMA_CERT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cert extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "CERT_ID")
    private Long id;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '인증서 타입'", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private CertType certType;

    @Column(length = 200, columnDefinition = "VARCHAR(200) COMMENT '인증서명'", nullable = false)
    @Size(max = 200)
    @NotBlank
    private String certName;

//    @OneToOne
//    @JoinColumn(name = "ROLE_ID")
//    private Role role;

    @OneToOne
    @JoinColumn(name = "SYSTEM_ID")
    private System system;

    @Column(length = 400, columnDefinition = "VARCHAR(400) COMMENT '내용'")
    @Size(max = 400)
    private String content;

    @Column(length = 400, columnDefinition = "VARCHAR(400) COMMENT '사이트주소'")
    @Size(max = 400)
    private String siteLink;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate startDate;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate endDate;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne
    @JoinColumn(name = "APPROVAL_ID")
    private Approval approval;

}
