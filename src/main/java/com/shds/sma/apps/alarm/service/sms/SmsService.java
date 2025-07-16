package com.shds.sma.apps.alarm.service.sms;

import com.shds.sma.apps.alarm.dto.AlarmRequestDto;
import com.shds.sma.apps.cert.dto.CertAlarmRequestDto;
import com.shds.sma.apps.ip.dto.IpAlarmRequestDto;
import com.shds.sma.apps.log.dto.LogAlarmRequestDto;

import java.util.List;

public interface SmsService {

    AlarmRequestDto sendSms(List<LogAlarmRequestDto> logs);

    List<AlarmRequestDto> sendIpSms(List<IpAlarmRequestDto> ips);

    List<AlarmRequestDto> sendCertSms(List<CertAlarmRequestDto> certs);

    List<AlarmRequestDto> sendIpToManagerSms(List<IpAlarmRequestDto> ips);

    List<AlarmRequestDto> sendCertToManagerSms(List<CertAlarmRequestDto> certs);
}
