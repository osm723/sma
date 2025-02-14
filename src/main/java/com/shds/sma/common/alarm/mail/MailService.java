package com.shds.sma.common.alarm.mail;

import com.shds.sma.common.log.dto.LogErrorResponseDto;
import jakarta.mail.MessagingException;

import java.util.List;

public interface MailService {

    void sendMail(List<LogErrorResponseDto> errorLogs);
}
