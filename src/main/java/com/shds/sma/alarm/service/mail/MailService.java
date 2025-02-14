package com.shds.sma.alarm.service.mail;

import com.shds.sma.log.dto.LogErrorResponseDto;

import java.util.List;

public interface MailService {

    void sendMail(List<LogErrorResponseDto> errorLogs);
}
