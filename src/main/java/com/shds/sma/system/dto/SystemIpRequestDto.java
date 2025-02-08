package com.shds.sma.system.dto;

import com.shds.sma.admin.entity.System;
import com.shds.sma.manage.dto.ip.IpRequestDto;
import com.shds.sma.manage.types.IpType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SystemIpRequestDto extends IpRequestDto {

    private IpType ipType;

    private String startIpAddr;

    private String endIpAddr;

    private String applySystemName;

    private String memberName;

    private String validity;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long systemId;

    private System system;

}
