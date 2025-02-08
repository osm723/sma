package com.shds.sma.admin.dto.system;

import com.shds.sma.admin.entity.Member;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
