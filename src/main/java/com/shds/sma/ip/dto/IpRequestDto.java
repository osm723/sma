package com.shds.sma.ip.dto;

import com.shds.sma.ip.types.IpType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IpRequestDto {

    private IpType ipType;

    private String startIpAddr;

    private String endIpAddr;

    private String port;

    private String applySystemName;

    private String memberName;

    private String validity;

    private LocalDate startDate;

    private LocalDate endDate;

}
