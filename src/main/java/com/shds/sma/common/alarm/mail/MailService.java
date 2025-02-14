package com.shds.sma.common.alarm.mail;

import com.shds.sma.log.dto.LogErrorResponseDto;

import java.util.List;

public interface MailService {

    void sendMail(List<LogErrorResponseDto> errorLogs);
}
