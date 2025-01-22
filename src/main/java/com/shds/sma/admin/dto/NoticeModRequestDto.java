package com.shds.sma.admin.dto;

import com.shds.sma.admin.types.NoticeType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class NoticeModRequestDto {

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

}
