package com.shds.sma.external.api.dto.notice;

import com.shds.sma.apps.admin.notice.dto.NoticeModRequestDto;
import com.shds.sma.apps.admin.notice.entity.type.NoticeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ApiNoticeModRequestDto extends NoticeModRequestDto {

    @NotNull
    private Long noticeId;

    @NotNull
    private NoticeType noticeType;

    @NotBlank
    private String subject;

    private String content;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

}
