package com.shds.sma.manage.dto.ip;

import com.shds.sma.admin.entity.Member;
import com.shds.sma.admin.entity.System;
import com.shds.sma.manage.entity.Approval;
import com.shds.sma.manage.entity.Ip;
import com.shds.sma.manage.types.IpType;
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

    private String ipAddr;

    private System system;

    private String content;

    private String siteLink;

    private LocalDate startDate;

    private LocalDate endDate;

    private Member member;

    private Approval approval;

    private LocalDateTime modDate;

    private Long modMemberId;

    private String validity;

    public IpResponseDto(Ip ip) {
        this.ipId = ip.getId();
        this.ipType = ip.getIpType();
        this.ipAddr = ip.getIpAddr();
        this.system = ip.getSystem();
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
