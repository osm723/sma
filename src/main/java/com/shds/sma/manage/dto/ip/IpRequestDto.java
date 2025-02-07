package com.shds.sma.manage.dto.ip;

import com.shds.sma.manage.types.IpType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IpRequestDto {

    private String startIpAddr;

    private String endIpAddr;

    private String systemName;

    private String memberName;

    private String validity;

    private LocalDate startDate;

    private LocalDate endDate;


}
