package com.shds.sma.common.log.dto;

import com.shds.sma.common.entity.Log;
import com.shds.sma.common.types.LogType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LogErrorResponseDto {

    private Long logId;

    private LogType logType;

    private String logCode;

    private String content;

    private Long memberId;

    private LocalDateTime logDate;

    public LogErrorResponseDto(LogType logType, String logCode, String content, Long memberId, LocalDateTime logDate) {
        this.logType = logType;
        this.logCode = logCode;
        this.content = content;
        this.memberId = memberId;
        this.logDate = logDate;
    }

    public LogErrorResponseDto(Log log) {
        this.logType = log.getLogType();
        this.logCode = log.getLogCode();
        this.content = log.getContent();
        this.memberId = log.getMemberId();
        this.logDate = log.getLogDate();
    }
}
