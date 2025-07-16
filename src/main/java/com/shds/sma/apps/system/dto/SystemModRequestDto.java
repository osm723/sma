package com.shds.sma.apps.system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemModRequestDto {

    private Long systemId;

    @NotBlank
    private String systemName;

    private Integer preIpAlarm;

    private Integer preCertAlarm;

    private String systemInfo;

}
