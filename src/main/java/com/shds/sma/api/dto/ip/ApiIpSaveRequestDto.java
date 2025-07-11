package com.shds.sma.api.dto.ip;

import com.shds.sma.api.dto.common.ApiApproval;
import com.shds.sma.ip.types.IpType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ApiIpSaveRequestDto {

    private IpType ipType;

    private String startIpAddr;

    private String endIpAddr;

    private String port;

    private String systemName;

    private String content;

    private String siteLink;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long memberId;

    private ApiApproval approval;
}
