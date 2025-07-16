package com.shds.sma.apps.alarm.service.kakao;

import com.shds.sma.apps.alarm.dto.AlarmRequestDto;
import com.shds.sma.apps.cert.dto.CertAlarmRequestDto;
import com.shds.sma.apps.ip.dto.IpAlarmRequestDto;
import com.shds.sma.apps.log.dto.LogAlarmRequestDto;

import java.util.List;

public interface KakaoAppService {

    AlarmRequestDto sendKakaoApp(List<LogAlarmRequestDto> logs);

    List<AlarmRequestDto> sendIpKakaoApp(List<IpAlarmRequestDto> ips);

    List<AlarmRequestDto> sendCertKakaoApp(List<CertAlarmRequestDto> certs);

    List<AlarmRequestDto> sendIpToManagerKakaoApp(List<IpAlarmRequestDto> ips);

    List<AlarmRequestDto> sendCertToManagerKakaoApp(List<CertAlarmRequestDto> certs);
}
