package com.shds.sma.api.dto.common;

import com.shds.sma.admin.entity.System;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiSystem {

    private String systemName;

    private Integer preIpAlarm;

    private Integer preCertAlarm;

    private String systemInfo;

    public ApiSystem(System system) {
        this.systemName = system.getSystemName();
        this.preIpAlarm = system.getPreIpAlarm();
        this.preCertAlarm = system.getPreCertAlarm();
        this.systemInfo = system.getSystemInfo();
    }
}
