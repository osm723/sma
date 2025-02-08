package com.shds.sma.system.dto;

import com.shds.sma.admin.entity.Member;
import com.shds.sma.admin.entity.System;
import com.shds.sma.common.entity.Approval;
import com.shds.sma.ip.entity.Ip;
import com.shds.sma.ip.types.IpType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SystemIpResponseDto {

    private Long ipId;

    private IpType ipType;

    private String startIpAddr;

    private String endIpAddr;

    private Long applySystemId;

    private System applySystem;

    private String content;

    private String siteLink;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long memberId;

    private Member member;

    private Approval approval;

    private LocalDateTime modDate;

    private Long modMemberId;

    private String validity;

    public SystemIpResponseDto(Ip ip) {
        this.ipId = ip.getId();
        this.ipType = ip.getIpType();
        this.startIpAddr = ip.getStartIpAddr();
        this.endIpAddr = ip.getEndIpAddr();
        this.applySystem = ip.getApplySystem();
        this.content = ip.getContent();
        this.siteLink = ip.getSiteLink();
        this.startDate = ip.getStartDate();
        this.endDate = ip.getEndDate();
        this.member = ip.getMember();
        this.approval = ip.getApproval();
        this.modDate = ip.getModDate();
        this.modMemberId = ip.getModMemberId();
        this.validity = ip.getValidity();
    }
}
