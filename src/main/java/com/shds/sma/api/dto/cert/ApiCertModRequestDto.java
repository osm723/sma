package com.shds.sma.api.dto.cert;

import com.shds.sma.admin.entity.Member;
import com.shds.sma.admin.entity.System;
import com.shds.sma.cert.dto.CertModRequestDto;
import com.shds.sma.cert.types.CertType;
import com.shds.sma.common.entity.Approval;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiCertModRequestDto extends CertModRequestDto {

    private Long certId;

    private CertType certType;

    private String certName;

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
