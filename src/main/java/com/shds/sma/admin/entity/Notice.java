package com.shds.sma.admin.entity;

import com.shds.sma.admin.dto.notice.NoticeModRequestDto;
import com.shds.sma.common.entity.BaseEntity;
import com.shds.sma.admin.types.NoticeType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "SMA_NOTICE")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "NOTICE_ID")
    private Long id;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '공지타입'", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private NoticeType noticeType;

    @Column(length = 100, columnDefinition = "VARCHAR(100) COMMENT '공지명'", nullable = false)
    @Size(max = 100)
    @NotBlank
    private String subject;

    @Column(length = 1000, columnDefinition = "VARCHAR(1000) COMMENT '공지내용'")
    @Size(max = 1000)
    private String content;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    @NotNull
    private LocalDate startDate;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    @NotNull
    private LocalDate endDate;

    public void noticeModified(NoticeModRequestDto noticeModRequestDto) {
        this.noticeType = noticeModRequestDto.getNoticeType();
        this.subject = noticeModRequestDto.getSubject();
        this.content = noticeModRequestDto.getContent();
        this.startDate = noticeModRequestDto.getStartDate();
        this.endDate = noticeModRequestDto.getEndDate();
    }


}
