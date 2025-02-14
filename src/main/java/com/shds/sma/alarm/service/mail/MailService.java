package com.shds.sma.alarm.service.mail;

import com.shds.sma.alarm.dto.AlarmRequestDto;
import com.shds.sma.cert.dto.CertAlarmRequestDto;
import com.shds.sma.ip.dto.IpAlarmRequestDto;
import com.shds.sma.log.dto.LogAlarmRequestDto;

import java.util.List;

public interface MailService {

    AlarmRequestDto sendLogMail(List<LogAlarmRequestDto> logs);

    List<AlarmRequestDto> sendIpMail(List<IpAlarmRequestDto> ips);

    List<AlarmRequestDto> sendCertMail(List<CertAlarmRequestDto> certs);

    List<AlarmRequestDto> sendIpToManagerMail(List<IpAlarmRequestDto> ips);

    List<AlarmRequestDto> sendCertToManagerMail(List<CertAlarmRequestDto> certs);
}
