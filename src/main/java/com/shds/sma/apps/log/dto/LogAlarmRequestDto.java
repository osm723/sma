package com.shds.sma.apps.log.dto;

import com.shds.sma.apps.log.entity.Log;
import com.shds.sma.apps.log.types.LogType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LogAlarmRequestDto {

    private Long logId;

    private LogType logType;

    private String logCode;

    private String content;

    private Long memberId;

    private LocalDateTime logDate;

    public LogAlarmRequestDto(LogType logType, String logCode, String content, Long memberId, LocalDateTime logDate) {
        this.logType = logType;
        this.logCode = logCode;
        this.content = content;
        this.memberId = memberId;
        this.logDate = logDate;
    }

    public LogAlarmRequestDto(Log log) {
        this.logType = log.getLogType();
        this.logCode = log.getLogCode();
        this.content = log.getContent();
        this.memberId = log.getMemberId();
        this.logDate = log.getLogDate();
    }
}
