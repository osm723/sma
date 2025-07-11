package com.shds.sma.system.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
