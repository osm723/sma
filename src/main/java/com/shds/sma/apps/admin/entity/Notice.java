package com.shds.sma.apps.admin.entity;

import com.shds.sma.apps.admin.dto.notice.NoticeModRequestDto;
import com.shds.sma.common.entity.BaseEntity;
import com.shds.sma.apps.admin.types.NoticeType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "SMA_NOTICE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "NOTICE_ID")
    private Long id;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '공지타입'", nullable = false)
    @Enumerated(EnumType.STRING)
    private NoticeType noticeType;

    @Column(length = 100, columnDefinition = "VARCHAR(100) COMMENT '공지명'", nullable = false)
    private String subject;

    @Column(length = 1000, columnDefinition = "VARCHAR(1000) COMMENT '공지내용'")
    private String content;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDate startDate;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDate endDate;

    public void noticeModified(NoticeModRequestDto noticeModRequestDto) {
        this.noticeType = noticeModRequestDto.getNoticeType();
        this.subject = noticeModRequestDto.getSubject();
        this.content = noticeModRequestDto.getContent();
        this.startDate = noticeModRequestDto.getStartDate();
        this.endDate = noticeModRequestDto.getEndDate();
    }

    @Builder
    public Notice(NoticeType noticeType, String subject, String content, LocalDate startDate, LocalDate endDate) {
        this.noticeType = noticeType;
        this.subject = subject;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
