package com.shds.sma.alarm.service.sms;

import com.shds.sma.log.dto.LogErrorResponseDto;

import java.util.List;

public interface SmsService {

    void sendSms(List<LogErrorResponseDto> errorLogs);
}
