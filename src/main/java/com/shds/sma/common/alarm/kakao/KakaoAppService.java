package com.shds.sma.common.alarm.kakao;

import com.shds.sma.common.log.dto.LogErrorResponseDto;

import java.util.List;

public interface KakaoAppService {

    void sendKakaoApp(List<LogErrorResponseDto> errorLogs);
}
