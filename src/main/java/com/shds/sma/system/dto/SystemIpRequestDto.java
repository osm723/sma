package com.shds.sma.system.dto;

import com.shds.sma.admin.entity.System;
import com.shds.sma.ip.dto.IpRequestDto;
import com.shds.sma.ip.types.IpType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SystemIpRequestDto {

    private String startIpAddr;

    private String endIpAddr;

    private String validity;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long systemId;

}
