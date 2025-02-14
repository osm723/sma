package com.shds.sma.alarm.service.kakao;

import com.shds.sma.alarm.dto.AlarmRequestDto;
import com.shds.sma.cert.dto.CertAlarmRequestDto;
import com.shds.sma.ip.dto.IpAlarmRequestDto;
import com.shds.sma.log.dto.LogAlarmRequestDto;

import java.util.List;

public interface KakaoAppService {

    AlarmRequestDto sendKakaoApp(List<LogAlarmRequestDto> logs);

    List<AlarmRequestDto> sendIpKakaoApp(List<IpAlarmRequestDto> ips);

    List<AlarmRequestDto> sendCertKakaoApp(List<CertAlarmRequestDto> certs);

    List<AlarmRequestDto> sendIpToManagerKakaoApp(List<IpAlarmRequestDto> ips);

    List<AlarmRequestDto> sendCertToManagerKakaoApp(List<CertAlarmRequestDto> certs);
}
