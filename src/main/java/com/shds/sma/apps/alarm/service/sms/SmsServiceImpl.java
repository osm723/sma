package com.shds.sma.apps.alarm.service.sms;

import com.shds.sma.apps.alarm.dto.AlarmRequestDto;
import com.shds.sma.apps.cert.dto.CertAlarmRequestDto;
import com.shds.sma.apps.ip.dto.IpAlarmRequestDto;
import com.shds.sma.apps.log.dto.LogAlarmRequestDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Primary
public class SmsServiceImpl implements SmsService {

    @Override
    public AlarmRequestDto sendSms(List<LogAlarmRequestDto> logs) {
        return null;
    }

    @Override
    public List<AlarmRequestDto> sendIpSms(List<IpAlarmRequestDto> ips) {
        return null;
    }

    @Override
    public List<AlarmRequestDto> sendCertSms(List<CertAlarmRequestDto> certs) {
        return null;
    }

    @Override
    public List<AlarmRequestDto> sendIpToManagerSms(List<IpAlarmRequestDto> ips) {
        return null;
    }

    @Override
    public List<AlarmRequestDto> sendCertToManagerSms(List<CertAlarmRequestDto> certs) {
        return null;
    }
}
