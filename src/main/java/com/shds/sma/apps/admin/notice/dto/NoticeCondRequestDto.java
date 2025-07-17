package com.shds.sma.apps.admin.notice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class NoticeCondRequestDto {

    private List<String> noticeType;

    private String subject;

    private LocalDate startDate;

    private LocalDate endDate;

    private String validity;

}
