package com.shds.sma.log.entity;

import com.shds.sma.log.types.LogType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "SMA_LOG")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Log {

    @Id
    @GeneratedValue
    @Column(name = "LOG_ID")
    private Long id;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '로그타입'", nullable = false)
    @Enumerated(EnumType.STRING)
    private LogType logType;

    @Column(length = 200, columnDefinition = "VARCHAR(200) COMMENT '로그 주내용'")
    private String logCode;

    @Column(length = 2000, columnDefinition = "VARCHAR(2000) COMMENT '로그내용'")
    private String content;

    @Column(columnDefinition = "BIGINT COMMENT 'memberId'")
    private Long memberId;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime logDate;
}
