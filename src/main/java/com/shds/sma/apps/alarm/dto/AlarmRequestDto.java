package com.shds.sma.apps.alarm.dto;

import com.shds.sma.apps.system.entity.System;
import com.shds.sma.apps.alarm.types.AlarmSendType;
import com.shds.sma.apps.alarm.types.PreAlarmTarget;
import com.shds.sma.apps.alarm.types.Sender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AlarmRequestDto {

    private AlarmSendType alarmSendType;

    private PreAlarmTarget preAlarmTarget;

    private System system;

    private Sender sender;

    private String subject;

    private String content;

    private Integer preAlarm;

    private LocalDateTime sendDate;

    private String isSuccess;

    @Builder
    public AlarmRequestDto(AlarmSendType alarmSendType, PreAlarmTarget preAlarmTarget, System system, Sender sender, String subject, String content, Integer preAlarm, LocalDateTime sendDate, String isSuccess) {
        this.alarmSendType = alarmSendType;
        this.preAlarmTarget = preAlarmTarget;
        this.system = system;
        this.sender = sender;
        this.subject = subject;
        this.content = content;
        this.preAlarm = preAlarm;
        this.sendDate = sendDate;
        this.isSuccess = isSuccess;
    }
}
