package com.shds.sma.apps.admin.notice.dto;

import com.shds.sma.apps.admin.notice.entity.Notice;
import com.shds.sma.apps.admin.notice.entity.type.NoticeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeHomeResponseDto {

    @NotNull
    private NoticeType noticeType;

    @NotBlank
    private String subject;

    private String content;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    public NoticeHomeResponseDto(Notice notice) {
        this.noticeType = notice.getNoticeType();
        this.subject = notice.getSubject();
        this.content = notice.getContent();
        this.startDate = notice.getStartDate();
        this.endDate = notice.getEndDate();
    }
}
