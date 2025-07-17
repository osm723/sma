package com.shds.sma.external.api.dto.cert;

import com.shds.sma.apps.admin.member.entity.Member;
import com.shds.sma.apps.system.entity.System;
import com.shds.sma.external.api.dto.common.ApiApproval;
import com.shds.sma.apps.cert.types.CertType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiCertModRequestDto {

    private Long certId;

    private CertType certType;

    private String certName;

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
