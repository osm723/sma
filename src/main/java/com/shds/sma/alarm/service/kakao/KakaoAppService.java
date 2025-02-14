package com.shds.sma.alarm.service.kakao;

import com.shds.sma.cert.dto.CertAlarmRequestDto;
import com.shds.sma.ip.dto.IpAlarmRequestDto;
import com.shds.sma.log.dto.LogAlarmRequestDto;

import java.util.List;

public interface KakaoAppService {

    void sendKakaoApp(List<LogAlarmRequestDto> logs);

    void sendIpKakaoApp(List<IpAlarmRequestDto> ips);

    void sendCertKakaoApp(List<CertAlarmRequestDto> certs);
}
