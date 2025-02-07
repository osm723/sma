package com.shds.sma.admin.dto.ip;

import com.shds.sma.admin.entity.Member;
import com.shds.sma.admin.entity.System;
import com.shds.sma.manage.entity.Approval;
import com.shds.sma.manage.types.IpType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class IpModRequestDto {

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
}
