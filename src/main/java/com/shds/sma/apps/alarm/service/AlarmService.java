package com.shds.sma.apps.alarm.service;

import com.shds.sma.apps.cert.dto.CertAlarmRequestDto;
import com.shds.sma.apps.ip.dto.IpAlarmRequestDto;
import com.shds.sma.apps.log.dto.LogAlarmRequestDto;

import java.util.List;

public interface AlarmService {

    void sendLogByKakaoApp(List<LogAlarmRequestDto> logs);

    void sendLogByMail(List<LogAlarmRequestDto> logs);

    void sendLogBySms(List<LogAlarmRequestDto> logs);

    void sendIpByKakaoApp(List<IpAlarmRequestDto> ips);

    void sendIpByMail(List<IpAlarmRequestDto> ips);

    void sendIpBySms(List<IpAlarmRequestDto> ips);

    void sendIpToManagerByKakaoApp(List<IpAlarmRequestDto> ips);

    void sendIpToManagerByMail(List<IpAlarmRequestDto> ips);

    void sendIpToManagerBySms(List<IpAlarmRequestDto> ips);

    void sendCertByKakaoApp(List<CertAlarmRequestDto> certs);

    void sendCertByMail(List<CertAlarmRequestDto> certs);

    void sendCertBySms(List<CertAlarmRequestDto> certs);

    void sendCertToManagerByKakaoApp(List<CertAlarmRequestDto> certs);

    void sendCertToManagerByMail(List<CertAlarmRequestDto> certs);

    void sendCertToManagerBySms(List<CertAlarmRequestDto> certs);

}
