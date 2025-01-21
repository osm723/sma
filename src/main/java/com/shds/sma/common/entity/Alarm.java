package com.shds.sma.common.entity;

import com.shds.sma.common.entity.types.AlarmSendType;
import com.shds.sma.common.entity.types.PreAlarmTarget;
import com.shds.sma.common.entity.types.Sender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "SMA_ALARM")
public class Alarm {

    @Id
    @GeneratedValue
    @Column(name = "ALARM_ID")
    private Long id;

    @Column(length = 10, columnDefinition = "VARCHAR(10) COMMENT '알림 전달 타입'")
    @NotBlank
    private AlarmSendType alarmSendType;

    @Column(length = 10, columnDefinition = "VARCHAR(10) COMMENT '알림 대상 (IP,인증서)'")
    @NotBlank
    private PreAlarmTarget preAlarmTarget;

    @OneToOne
    @JoinColumn(name = "SYSTEM_ID")
    private System system;

    @Column(length = 10, columnDefinition = "VARCHAR(10) COMMENT '송신자'")
    @NotBlank
    private Sender sender;

    @Column(length = 100, columnDefinition = "VARCHAR(100) COMMENT '알림명'")
    @NotBlank
    private String subject;

    @Column(length = 1000, columnDefinition = "VARCHAR(1000) COMMENT '알림내용'")
    private String content;

    @Column(length = 2, columnDefinition = "INT COMMENT '만료 전 알림 일자'")
    @Min(1)
    @Max(99)
    private Integer preAlarm;

    @Column(length = 14, columnDefinition = "VARCHAR(14) COMMENT '발송일자'")
    @NotBlank
    private LocalDateTime sendDate;

    @Column(length = 1, columnDefinition = "VARCHAR(14) COMMENT '발송 성공유무'")
    private String isSuccess;

}
