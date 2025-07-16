package com.shds.sma.apps.ip.dto;

import com.shds.sma.apps.admin.entity.Member;
import com.shds.sma.apps.system.entity.System;
import com.shds.sma.apps.admin.entity.Approval;
import com.shds.sma.common.types.ApprovalStatus;
import com.shds.sma.common.types.Degree;
import com.shds.sma.apps.ip.entity.Ip;
import com.shds.sma.apps.ip.types.IpType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IpResponseDto {

    private Long ipId;

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

    public IpResponseDto(Ip ip) {
        this.ipId = ip.getId();
        this.ipType = ip.getIpType();
        this.startIpAddr = ip.getStartIpAddr();
        this.endIpAddr = ip.getEndIpAddr();
        this.port = ip.getPort();
        this.applySystem = ip.getApplySystem();
        this.content = ip.getContent();
        this.siteLink = ip.getSiteLink();
        this.startDate = ip.getStartDate();
        this.endDate = ip.getEndDate();
        this.member = ip.getMember();
        this.approval = ip.getApproval();
        this.modDate = ip.getModDate();
        this.modMemberId = ip.getModMemberId();
        this.validity = ip.getValidity().name();
    }
}
