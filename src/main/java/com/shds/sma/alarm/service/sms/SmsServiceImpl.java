package com.shds.sma.alarm.service.sms;

import com.shds.sma.log.dto.LogErrorResponseDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Primary
public class SmsServiceImpl implements SmsService {

    @Override
    public void sendSms(List<LogErrorResponseDto> errorLogs) {

    }
}
