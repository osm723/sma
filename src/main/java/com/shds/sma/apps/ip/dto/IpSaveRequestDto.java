package com.shds.sma.apps.ip.dto;

import com.shds.sma.apps.admin.entity.Member;
import com.shds.sma.apps.system.entity.System;
import com.shds.sma.apps.admin.entity.Approval;
import com.shds.sma.common.types.ApprovalStatus;
import com.shds.sma.common.types.Degree;
import com.shds.sma.apps.ip.types.IpType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class IpSaveRequestDto {

    private IpType ipType;

    private String startIpAddr;

    private String endIpAddr;

    private String port;

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
