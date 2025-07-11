package com.shds.sma.system.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SystemSaveRequestDto {

    private String systemName;

    //private Long systemManagerId;

    //private Member systemManagers;

    private Integer preIpAlarm;

    private Integer preCertAlarm;

    private String systemInfo;

}
