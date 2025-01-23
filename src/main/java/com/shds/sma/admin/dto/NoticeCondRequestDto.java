package com.shds.sma.admin.dto;

import com.shds.sma.admin.types.NoticeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NoticeCondRequestDto {

    private NoticeType noticeType;

    private String subject;

    private LocalDate startDate;

    private LocalDate endDate;

    private String validity;

}
