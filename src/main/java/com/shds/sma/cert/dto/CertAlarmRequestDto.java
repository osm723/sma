package com.shds.sma.cert.dto;

import com.shds.sma.admin.entity.Member;
import com.shds.sma.admin.entity.System;
import com.shds.sma.cert.entity.Cert;
import com.shds.sma.cert.types.CertType;
import com.shds.sma.common.entity.Approval;
import com.shds.sma.common.types.ApprovalStatus;
import com.shds.sma.common.types.Degree;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CertAlarmRequestDto {

    private Long certId;

    private CertType certType;

    private String certName;

    private Long applySystemId;

    private System applySystem;

    private String content;

    private String siteLink;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long memberId;

    private Member member;

    private LocalDateTime modDate;

    private Long modMemberId;

    private String validity;

    // 결재

    private Approval approval;

    private Long approvalId;

    private String approvalNo;

    private Long drafterId;

    private Degree degree;

    private Long approverId;

    private ApprovalStatus approvalStatus;

    private LocalDateTime approveDate;

    private LocalDateTime cancelDate;

    public CertAlarmRequestDto(Cert cert) {
        this.certId = cert.getId();
        this.certType = cert.getCertType();
        this.certName = cert.getCertName();
        this.applySystem = cert.getApplySystem();
        this.content = cert.getContent();
        this.siteLink = cert.getSiteLink();
        this.startDate = cert.getStartDate();
        this.endDate = cert.getEndDate();
        this.member = cert.getMember();
        this.approval = cert.getApproval();
        this.modDate = cert.getModDate();
        this.modMemberId = cert.getModMemberId();
        this.validity = cert.getValidity();
    }
}
