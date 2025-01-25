package com.shds.sma.admin.dto.system;

import com.shds.sma.admin.entity.System;
import com.shds.sma.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SystemResponseDto {

    @NotNull
    private Long id;

    @NotBlank
    private String systemName;

    private List<Member> systemManagers;

    private Integer preIpAlarm;

    private Integer preCertAlarm;

    public SystemResponseDto(System system) {
        this.id = system.getId();
        this.systemName = system.getSystemName();
        this.systemManagers = system.getSystemManagers();
        this.preIpAlarm = system.getPreIpAlarm();
        this.preCertAlarm = system.getPreCertAlarm();
    }
}
