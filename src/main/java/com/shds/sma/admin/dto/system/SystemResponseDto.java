package com.shds.sma.admin.dto.system;

import com.shds.sma.admin.entity.System;
import com.shds.sma.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SystemResponseDto {

    @NotNull
    private Long systemId;

    @NotBlank
    private String systemName;

    private List<Member> systemManagers;

    private Integer preIpAlarm;

    private Integer preCertAlarm;

    public SystemResponseDto(System system) {
        this.systemId = system.getId();
        this.systemName = system.getSystemName();
        this.systemManagers = system.getSystemManagers();
        this.preIpAlarm = system.getPreIpAlarm();
        this.preCertAlarm = system.getPreCertAlarm();
    }
}
