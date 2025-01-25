package com.shds.sma.admin.dto.system;

import com.shds.sma.member.entity.Member;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SystemSaveRequestDto {

    @NotNull
    private String systemName;

    private List<Member> systemManagers;

    private Integer preIpAlarm;

    private Integer preCertAlarm;

}
