package com.shds.sma.alarm.entity;

import com.shds.sma.alarm.types.AlarmSendType;
import com.shds.sma.alarm.types.PreAlarmTarget;
import com.shds.sma.alarm.types.Sender;
import com.shds.sma.system.entity.System;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "SMA_ALARM")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm {

    @Id
    @GeneratedValue
    @Column(name = "ALARM_ID")
    private Long id;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '알림 전달 타입'", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private AlarmSendType alarmSendType;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '알림 대상 (IP,인증서)'", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private PreAlarmTarget preAlarmTarget;

    @OneToOne
    @JoinColumn(name = "SYSTEM_ID")
    private System system;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '송신자'", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Sender sender;

    @Column(length = 100, columnDefinition = "VARCHAR(100) COMMENT '알림명'", nullable = false)
    @Size(max = 100)
    @NotBlank
    private String subject;

    @Column(length = 4000, columnDefinition = "VARCHAR(4000) COMMENT '알림내용'")
    private String content;

    @Column(length = 2, columnDefinition = "INT COMMENT '만료 전 알림 일자'")
    @Min(0)
    @Max(99)
    private Integer preAlarm;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    @NotNull
    private LocalDateTime sendDate;

    @Column(length = 1, columnDefinition = "VARCHAR(1) COMMENT '발송 성공유무'")
    @Size(max = 1)
    private String isSuccess;

}
