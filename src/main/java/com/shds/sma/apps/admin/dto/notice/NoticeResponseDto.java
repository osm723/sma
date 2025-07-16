package com.shds.sma.apps.admin.dto.notice;

import com.shds.sma.apps.admin.entity.Notice;
import com.shds.sma.apps.admin.types.NoticeType;
import com.shds.sma.common.types.ValidityStatus;
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
public class NoticeResponseDto {

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
    private ValidityStatus validity;

    public NoticeResponseDto(Notice notice) {
        this.id = notice.getId();
        this.noticeType = notice.getNoticeType();
        this.subject = notice.getSubject();
        this.content = notice.getContent();
        this.startDate = notice.getStartDate();
        this.endDate = notice.getEndDate();
        this.modDate = notice.getModDate();
        this.modMemberId = notice.getModMemberId();
        this.validity = notice.getValidity();
    }
}
