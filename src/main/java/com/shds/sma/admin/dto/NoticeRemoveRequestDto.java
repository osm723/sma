package com.shds.sma.admin.dto;

import com.shds.sma.admin.types.NoticeType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoticeRemoveRequestDto {

    @NotBlank
    private Long id;

}
