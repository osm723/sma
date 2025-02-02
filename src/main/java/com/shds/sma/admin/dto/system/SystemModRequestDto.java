package com.shds.sma.admin.dto.system;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemModRequestDto {

    private Long systemId;

    @NotBlank
    private String systemName;

    //private List<Member> systemManagers;

    private Integer preIpAlarm;

    private Integer preCertAlarm;

}
