package com.shds.sma.common.alarm.sms;

import com.shds.sma.common.log.dto.LogErrorResponseDto;
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
