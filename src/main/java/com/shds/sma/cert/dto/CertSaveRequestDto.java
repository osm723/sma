package com.shds.sma.cert.dto;

import com.shds.sma.admin.entity.Member;
import com.shds.sma.admin.entity.System;
import com.shds.sma.common.entity.Approval;
import com.shds.sma.cert.types.CertType;
import com.shds.sma.common.types.ApprovalStatus;
import com.shds.sma.common.types.Degree;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CertSaveRequestDto {

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

    private boolean useApproval;

    private String approvalNo;

    private Long drafterId;

    private Degree degree;

    private Long approverId;

    private ApprovalStatus approvalStatus;

    private LocalDateTime approveDate;

    private LocalDateTime cancelDate;

    public boolean useApproval() {
        return this.useApproval;
    }
}
