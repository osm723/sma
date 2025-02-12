package com.shds.sma.api.dto.ip;

import com.shds.sma.admin.entity.Member;
import com.shds.sma.admin.entity.System;
import com.shds.sma.api.dto.common.ApiApproval;
import com.shds.sma.common.entity.Approval;
import com.shds.sma.ip.dto.IpModRequestDto;
import com.shds.sma.ip.types.IpType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ApiIpModRequestDto extends IpModRequestDto {

    private Long ipId;

    private IpType ipType;

    private String startIpAddr;

    private String endIpAddr;

    private String systemName;

    private System system;

    private String content;

    private String siteLink;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long memberId;

    private Member member;

    private ApiApproval approval;
}
