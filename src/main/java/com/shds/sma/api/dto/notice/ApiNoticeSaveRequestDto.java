package com.shds.sma.api.dto.notice;

import com.shds.sma.admin.types.NoticeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ApiNoticeSaveRequestDto {

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
