package com.shds.sma.apps.system.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
