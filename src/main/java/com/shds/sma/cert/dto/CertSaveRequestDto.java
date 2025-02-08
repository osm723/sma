package com.shds.sma.cert.dto;

import com.shds.sma.admin.entity.Member;
import com.shds.sma.admin.entity.System;
import com.shds.sma.common.entity.Approval;
import com.shds.sma.cert.types.CertType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CertSaveRequestDto {

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
