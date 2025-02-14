package com.shds.sma.common.alarm.sms;

import com.shds.sma.common.log.dto.LogErrorResponseDto;

import java.util.List;

public interface SmsService {

    void sendSms(List<LogErrorResponseDto> errorLogs);
}
