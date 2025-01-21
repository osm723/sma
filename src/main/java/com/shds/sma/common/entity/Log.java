package com.shds.sma.common.entity;

import com.shds.sma.common.entity.types.LogType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "SMA_LOG")
public class Log {

    @Id
    @GeneratedValue
    @Column(name = "LOG_ID")
    private Long id;

    @Column(length = 10, columnDefinition = "VARCHAR(10) COMMENT '로그타입'")
    @NotBlank
    private LogType logType;

    @Column(length = 1000, columnDefinition = "VARCHAR(1000) COMMENT '로그내용'")
    private String content;

    @Column(length = 40, columnDefinition = "VARCHAR(40) COMMENT 'memberId'")
    private String memberId;

    @Column(length = 14, columnDefinition = "VARCHAR(14) COMMENT '등록일시'")
    @NotBlank
    private LocalDateTime logDate;


}
