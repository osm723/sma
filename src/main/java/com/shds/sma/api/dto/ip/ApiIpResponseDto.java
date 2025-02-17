package com.shds.sma.api.dto.ip;

import com.shds.sma.admin.entity.Member;
import com.shds.sma.admin.entity.System;
import com.shds.sma.api.dto.common.ApiApproval;
import com.shds.sma.api.dto.common.ApiMember;
import com.shds.sma.api.dto.common.ApiSystem;
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
public class ApiIpResponseDto {

    private Long ipId;

    private IpType ipType;

    private String startIpAddr;

    private String endIpAddr;

    private String port;

    private ApiSystem applySystem;

    private String content;

    private String siteLink;

    private LocalDate startDate;

    private LocalDate endDate;

    private ApiMember member;

    private ApiApproval approval;

    private String validity;

    public ApiIpResponseDto(Ip ip) {
        this.ipId = ip.getId();
        this.ipType = ip.getIpType();
        this.startIpAddr = ip.getStartIpAddr();
        this.endIpAddr = ip.getEndIpAddr();
        this.port = ip.getPort();
        this.applySystem = new ApiSystem(ip.getApplySystem());
        this.content = ip.getContent();
        this.siteLink = ip.getSiteLink();
        this.startDate = ip.getStartDate();
        this.endDate = ip.getEndDate();
        this.member = new ApiMember(ip.getMember());
        this.approval = new ApiApproval(ip.getApproval());
        this.validity = ip.getValidity();
    }
}
