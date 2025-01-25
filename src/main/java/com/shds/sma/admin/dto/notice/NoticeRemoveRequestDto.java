package com.shds.sma.admin.dto.notice;

import com.shds.sma.admin.types.NoticeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoticeRemoveRequestDto {

    @NotNull
    private Long noticeId;

}
