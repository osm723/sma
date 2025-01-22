package com.shds.sma.common.entity;

import com.shds.sma.common.entity.types.LogType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "SMA_LOG")
public class Log {

    @Id
    @GeneratedValue
    @Column(name = "LOG_ID")
    private Long id;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '로그타입'", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private LogType logType;

    @Column(length = 1000, columnDefinition = "VARCHAR(1000) COMMENT '로그내용'")
    @Size(max = 1000)
    private String content;

    @Column(columnDefinition = "BIGINT COMMENT 'memberId'")
    private Long memberId;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    @NotNull
    private LocalDateTime logDate;


}
