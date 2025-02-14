package com.shds.sma.alarm.service.sms;

import com.shds.sma.cert.dto.CertAlarmRequestDto;
import com.shds.sma.ip.dto.IpAlarmRequestDto;
import com.shds.sma.log.dto.LogAlarmRequestDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Primary
public class SmsServiceImpl implements SmsService {

    @Override
    public void sendSms(List<LogAlarmRequestDto> logs) {

    }

    @Override
    public void sendIpSms(List<IpAlarmRequestDto> ips) {

    }

    @Override
    public void sendCertSms(List<CertAlarmRequestDto> certs) {

    }
}
