package com.shds.sma.log.dto;

import com.shds.sma.log.types.LogType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LogRequestDto {

    private LogType logType;

    private String logCode;

    private String content;

    private Long memberId;

    private LocalDateTime logDate;

    public LogRequestDto(LogType logType, String logCode, String content, Long memberId, LocalDateTime logDate) {
        this.logType = logType;
        this.logCode = logCode;
        this.content = content;
        this.memberId = memberId;
        this.logDate = logDate;
    }
}
