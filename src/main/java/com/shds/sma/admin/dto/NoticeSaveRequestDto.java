package com.shds.sma.admin.dto;

import com.shds.sma.admin.entity.Notice;
import com.shds.sma.admin.types.NoticeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class NoticeSaveRequestDto {

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
