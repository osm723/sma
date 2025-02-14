package com.shds.sma.alarm.service.sms;

import com.shds.sma.cert.dto.CertAlarmRequestDto;
import com.shds.sma.ip.dto.IpAlarmRequestDto;
import com.shds.sma.log.dto.LogAlarmRequestDto;

import java.util.List;

public interface SmsService {

    void sendSms(List<LogAlarmRequestDto> logs);

    void sendIpSms(List<IpAlarmRequestDto> ips);

    void sendCertSms(List<CertAlarmRequestDto> certs);
}
