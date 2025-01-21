package com.shds.sma.common.entity;

import com.shds.sma.common.entity.types.NoticeType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Table(name = "SMA_NOTICE")
@Getter
@Setter
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "NOTICE_ID")
    private Long id;

    @Column(length = 10, columnDefinition = "VARCHAR(10) COMMENT '공지타입'")
    @NotBlank
    private NoticeType noticeType;

    @Column(length = 100, columnDefinition = "VARCHAR(100) COMMENT '공지명'")
    @NotBlank
    private String subject;

    @Column(length = 1000, columnDefinition = "VARCHAR(1000) COMMENT '공지내용'")
    private String content;

    @Column(length = 14, columnDefinition = "VARCHAR(14) COMMENT '시작일자'")
    @NotBlank
    private LocalDateTime startDate;

    @Column(length = 14, columnDefinition = "VARCHAR(14) COMMENT '종료일자'")
    @NotBlank
    private LocalDateTime endDate;


}
