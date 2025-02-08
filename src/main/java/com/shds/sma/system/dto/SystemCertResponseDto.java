package com.shds.sma.system.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.shds.sma.admin.entity.Member;
import com.shds.sma.admin.entity.System;
import com.shds.sma.common.entity.Approval;
import com.shds.sma.cert.entity.Cert;
import com.shds.sma.cert.types.CertType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SystemCertResponseDto {

    private String certName;

    private String applySystemName;

    @QueryProjection
    public SystemCertResponseDto(String certName, String applySystemName) {
        this.certName = certName;
        this.applySystemName = applySystemName;
    }
}
