package com.shds.sma.apps.alarm.service.mail;

import com.shds.sma.apps.alarm.dto.AlarmRequestDto;
import com.shds.sma.apps.cert.dto.CertAlarmRequestDto;
import com.shds.sma.apps.ip.dto.IpAlarmRequestDto;
import com.shds.sma.apps.log.dto.LogAlarmRequestDto;

import java.util.List;

public interface MailService {

    AlarmRequestDto sendLogMail(List<LogAlarmRequestDto> logs);

    List<AlarmRequestDto> sendIpMail(List<IpAlarmRequestDto> ips);

    List<AlarmRequestDto> sendCertMail(List<CertAlarmRequestDto> certs);

    List<AlarmRequestDto> sendIpToManagerMail(List<IpAlarmRequestDto> ips);

    List<AlarmRequestDto> sendCertToManagerMail(List<CertAlarmRequestDto> certs);
}
