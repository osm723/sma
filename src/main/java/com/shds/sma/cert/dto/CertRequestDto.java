package com.shds.sma.cert.dto;

import com.shds.sma.cert.types.CertType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CertRequestDto {

    private CertType certType;

    private String certName;

    private String applySystemName;

    private String memberName;

    private String validity;

    private LocalDate startDate;

    private LocalDate endDate;
}
