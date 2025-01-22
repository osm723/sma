package com.shds.sma.admin.dto;

import com.shds.sma.admin.entity.Notice;
import com.shds.sma.admin.types.NoticeType;
import com.shds.sma.common.entity.BaseEntity;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private Long id;

    @NotBlank
    private NoticeType noticeType;

    @NotBlank
    private String subject;

    private String content;

    @NotBlank
    private LocalDate startDate;

    @NotBlank
    private LocalDate endDate;

    @NotBlank
    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private LocalDateTime modDate;

    private Long modMemberId;

    @NotBlank
    private String validity;

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
