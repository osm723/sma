package com.shds.sma.cert.entity;

import com.shds.sma.admin.entity.Member;
import com.shds.sma.common.entity.BaseEntity;
import com.shds.sma.admin.entity.System;
import com.shds.sma.cert.dto.CertModRequestDto;
import com.shds.sma.manage.entity.Approval;
import com.shds.sma.cert.types.CertType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
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

    @ManyToOne
    @JoinColumn(name = "SYSTEM_ID")
    private System applySystem;

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

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne
    @JoinColumn(name = "APPROVAL_ID")
    private Approval approval;

    public void certModified(CertModRequestDto certModRequestDto) {
        this.certType = certModRequestDto.getCertType();
        this.certName = certModRequestDto.getCertName();
        this.applySystem = certModRequestDto.getApplySystem();
        this.content = certModRequestDto.getContent();
        this.siteLink = certModRequestDto.getSiteLink();
        this.startDate = certModRequestDto.getStartDate();
        this.endDate = certModRequestDto.getEndDate();
        this.member = certModRequestDto.getMember();
        this.approval = certModRequestDto.getApproval();
    }

    @Builder
    public Cert(CertType certType, String certName, System applySystem, String content, String siteLink, LocalDate startDate, LocalDate endDate, Member member, Approval approval) {
        this.certType = certType;
        this.certName = certName;
        this.applySystem = applySystem;
        this.content = content;
        this.siteLink = siteLink;
        this.startDate = startDate;
        this.endDate = endDate;
        this.member = member;
        this.approval = approval;
    }
}
