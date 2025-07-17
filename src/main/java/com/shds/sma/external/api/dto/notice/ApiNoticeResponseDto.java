package com.shds.sma.external.api.dto.notice;

import com.shds.sma.apps.admin.notice.entity.Notice;
import com.shds.sma.apps.admin.notice.entity.type.NoticeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiNoticeResponseDto {

    @NotNull
    private Long id;

    @NotNull
    private NoticeType noticeType;

    @NotBlank
    private String subject;

    private String content;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private LocalDateTime modDate;

    private Long modMemberId;

    @NotNull
    private String validity;

    public ApiNoticeResponseDto(Notice notice) {
        this.id = notice.getId();
        this.noticeType = notice.getNoticeType();
        this.subject = notice.getSubject();
        this.content = notice.getContent();
        this.startDate = notice.getStartDate();
        this.endDate = notice.getEndDate();
        this.modDate = notice.getModDate();
        this.modMemberId = notice.getModMemberId();
        this.validity = notice.getValidity().name();
    }
}
