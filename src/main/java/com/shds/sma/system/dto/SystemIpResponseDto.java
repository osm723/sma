package com.shds.sma.system.dto;

import com.querydsl.core.annotations.QueryProjection;
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

    private String startIpAddr;

    private String endIpAddr;

    private String port;

    private String applySystemName;

    @QueryProjection
    public SystemIpResponseDto(String startIpAddr, String endIpAddr, String port, String applySystemName) {
        this.startIpAddr = startIpAddr;
        this.endIpAddr = endIpAddr;
        this.port = port;
        this.applySystemName = applySystemName;
    }
}
