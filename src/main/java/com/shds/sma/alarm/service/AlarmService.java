package com.shds.sma.alarm.service;

import com.shds.sma.log.dto.LogErrorResponseDto;

import java.util.List;

public interface AlarmService {

    void sendKakaoApp(List<LogErrorResponseDto> errorLogs);

    void sendMail(List<LogErrorResponseDto> errorLogs);

    void sendSms(List<LogErrorResponseDto> errorLogs);
}
