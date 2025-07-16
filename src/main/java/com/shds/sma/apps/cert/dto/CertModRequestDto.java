package com.shds.sma.apps.cert.dto;

import com.shds.sma.apps.admin.entity.Member;
import com.shds.sma.apps.system.entity.System;
import com.shds.sma.apps.admin.entity.Approval;
import com.shds.sma.apps.cert.types.CertType;
import com.shds.sma.common.types.ApprovalStatus;
import com.shds.sma.common.types.Degree;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CertModRequestDto {

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
}
