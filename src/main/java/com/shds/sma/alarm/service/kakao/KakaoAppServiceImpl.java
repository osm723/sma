package com.shds.sma.alarm.service.kakao;

import com.shds.sma.alarm.dto.AlarmRequestDto;
import com.shds.sma.cert.dto.CertAlarmRequestDto;
import com.shds.sma.ip.dto.IpAlarmRequestDto;
import com.shds.sma.log.dto.LogAlarmRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class KakaoAppServiceImpl implements KakaoAppService {

    @Override
    public AlarmRequestDto sendKakaoApp(List<LogAlarmRequestDto> logs) {
        return null;
    }

    @Override
    public List<AlarmRequestDto> sendIpKakaoApp(List<IpAlarmRequestDto> ips) {
        return null;
    }

    @Override
    public List<AlarmRequestDto> sendCertKakaoApp(List<CertAlarmRequestDto> certs) {
        return null;
    }

    @Override
    public List<AlarmRequestDto> sendIpToManagerKakaoApp(List<IpAlarmRequestDto> ips) {
        return null;
    }

    @Override
    public List<AlarmRequestDto> sendCertToManagerKakaoApp(List<CertAlarmRequestDto> certs) {
        return null;
    }
}
