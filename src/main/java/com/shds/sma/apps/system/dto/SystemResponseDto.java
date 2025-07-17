package com.shds.sma.apps.system.dto;

import com.shds.sma.apps.system.entity.System;
import com.shds.sma.apps.admin.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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

    private String systemInfo;

    private LocalDateTime modDate;

    private Long modMemberId;

    private String validity;

    public SystemResponseDto(System system) {
        this.systemId = system.getId();
        this.systemName = system.getSystemName();
        this.systemManagers = system.getSystemManagers();
        this.preIpAlarm = system.getPreIpAlarm();
        this.preCertAlarm = system.getPreCertAlarm();
        this.systemInfo = system.getSystemInfo();
        this.modDate = system.getModDate();
        this.modMemberId = system.getModMemberId();
        this.validity = system.getValidity().name();
    }
}
