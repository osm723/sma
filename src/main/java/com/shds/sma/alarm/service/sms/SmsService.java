package com.shds.sma.alarm.service.sms;

import com.shds.sma.alarm.dto.AlarmRequestDto;
import com.shds.sma.cert.dto.CertAlarmRequestDto;
import com.shds.sma.ip.dto.IpAlarmRequestDto;
import com.shds.sma.log.dto.LogAlarmRequestDto;

import java.util.List;

public interface SmsService {

    AlarmRequestDto sendSms(List<LogAlarmRequestDto> logs);

    List<AlarmRequestDto> sendIpSms(List<IpAlarmRequestDto> ips);

    List<AlarmRequestDto> sendCertSms(List<CertAlarmRequestDto> certs);

    List<AlarmRequestDto> sendIpToManagerSms(List<IpAlarmRequestDto> ips);

    List<AlarmRequestDto> sendCertToManagerSms(List<CertAlarmRequestDto> certs);
}
