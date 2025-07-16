package com.shds.sma.apps.system.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SystemCertRequestDto {

    private String certName;

    private String validity;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long systemId;

}
