package com.shds.sma.alarm.service.kakao;

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
    public void sendKakaoApp(List<LogAlarmRequestDto> logs) {

    }

    @Override
    public void sendIpKakaoApp(List<IpAlarmRequestDto> ips) {

    }

    @Override
    public void sendCertKakaoApp(List<CertAlarmRequestDto> certs) {

    }

    @Override
    public void sendIpToManagerKakaoApp(List<IpAlarmRequestDto> ips) {

    }

    @Override
    public void sendCertToManagerKakaoApp(List<CertAlarmRequestDto> certs) {

    }
}
