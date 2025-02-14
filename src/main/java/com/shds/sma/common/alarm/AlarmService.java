package com.shds.sma.common.alarm;

import com.shds.sma.common.log.dto.LogErrorResponseDto;

import java.util.List;

public interface AlarmService {

    void sendKakaoApp(List<LogErrorResponseDto> errorLogs);

    void sendMail(List<LogErrorResponseDto> errorLogs);

    void sendSms(List<LogErrorResponseDto> errorLogs);
}
