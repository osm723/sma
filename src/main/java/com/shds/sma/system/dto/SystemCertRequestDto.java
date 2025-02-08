package com.shds.sma.system.dto;

import com.shds.sma.admin.entity.System;
import com.shds.sma.manage.dto.cert.CertRequestDto;
import com.shds.sma.manage.types.CertType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SystemCertRequestDto extends CertRequestDto {

    private CertType certType;

    private String certName;

    private String applySystemName;

    private String memberName;

    private String validity;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long systemId;

    private System system;

}
