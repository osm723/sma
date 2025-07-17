package com.shds.sma.apps.cert.entity;

import com.shds.sma.apps.admin.member.entity.Member;
import com.shds.sma.external.api.dto.cert.ApiCertModRequestDto;
import com.shds.sma.common.entity.BaseEntity;
import com.shds.sma.apps.system.entity.System;
import com.shds.sma.apps.cert.dto.CertModRequestDto;
import com.shds.sma.apps.admin.common.entity.Approval;
import com.shds.sma.apps.cert.types.CertType;
import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private CertType certType;

    @Column(length = 200, columnDefinition = "VARCHAR(200) COMMENT '인증서명'", nullable = false)
    private String certName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SYSTEM_ID")
    private System applySystem;

    @Column(length = 400, columnDefinition = "VARCHAR(400) COMMENT '내용'")
    private String content;

    @Column(length = 400, columnDefinition = "VARCHAR(400) COMMENT '사이트주소'")
    private String siteLink;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate startDate;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne(cascade = CascadeType.PERSIST)
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

    public void apiCertModified(ApiCertModRequestDto apiCertModRequestDto) {
        this.certType = apiCertModRequestDto.getCertType();
        this.certName = apiCertModRequestDto.getCertName();
        this.applySystem = apiCertModRequestDto.getSystem();
        this.content = apiCertModRequestDto.getContent();
        this.siteLink = apiCertModRequestDto.getSiteLink();
        this.startDate = apiCertModRequestDto.getStartDate();
        this.endDate = apiCertModRequestDto.getEndDate();
        this.member = apiCertModRequestDto.getMember();
        this.approval = new Approval(apiCertModRequestDto.getApproval());
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
