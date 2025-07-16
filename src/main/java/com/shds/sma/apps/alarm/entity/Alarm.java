package com.shds.sma.apps.alarm.entity;

import com.shds.sma.apps.alarm.types.AlarmSendType;
import com.shds.sma.apps.alarm.types.PreAlarmTarget;
import com.shds.sma.apps.alarm.types.Sender;
import com.shds.sma.apps.system.entity.System;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "SMA_ALARM")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm {

    @Id
    @GeneratedValue
    @Column(name = "ALARM_ID")
    private Long id;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '알림 전달 타입'", nullable = false)
    @Enumerated(EnumType.STRING)
    private AlarmSendType alarmSendType;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '알림 대상 (IP,인증서)'", nullable = false)
    @Enumerated(EnumType.STRING)
    private PreAlarmTarget preAlarmTarget;

    @OneToOne
    @JoinColumn(name = "SYSTEM_ID")
    private System system;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '송신자'", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sender sender;

    @Column(length = 100, columnDefinition = "VARCHAR(100) COMMENT '알림명'", nullable = false)
    private String subject;

    @Column(length = 4000, columnDefinition = "VARCHAR(4000) COMMENT '알림내용'")
    private String content;

    @Column(length = 2, columnDefinition = "INT COMMENT '만료 전 알림 일자'")
    private Integer preAlarm;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime sendDate;

    @Column(length = 1, columnDefinition = "VARCHAR(1) COMMENT '발송 성공유무'")
    private String isSuccess;

    @Builder
    public Alarm(AlarmSendType alarmSendType, PreAlarmTarget preAlarmTarget, System system, Sender sender, String subject, String content, Integer preAlarm, LocalDateTime sendDate, String isSuccess) {
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
