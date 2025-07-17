package com.shds.sma.apps.admin.notice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeRemoveRequestDto {

    @NotNull
    private Long noticeId;

}
