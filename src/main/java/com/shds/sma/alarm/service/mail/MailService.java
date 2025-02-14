package com.shds.sma.alarm.service.mail;

import com.shds.sma.cert.dto.CertAlarmRequestDto;
import com.shds.sma.ip.dto.IpAlarmRequestDto;
import com.shds.sma.log.dto.LogAlarmRequestDto;

import java.util.List;

public interface MailService {

    void sendLogMail(List<LogAlarmRequestDto> logs);

    void sendIpMail(List<IpAlarmRequestDto> ips);

    void sendCertMail(List<CertAlarmRequestDto> certs);
}
