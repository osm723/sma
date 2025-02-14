package com.shds.sma.alarm.service;

import com.shds.sma.alarm.service.kakao.KakaoAppService;
import com.shds.sma.alarm.service.mail.MailService;
import com.shds.sma.alarm.service.sms.SmsService;
import com.shds.sma.alarm.repository.AlarmRepository;
import com.shds.sma.cert.dto.CertAlarmRequestDto;
import com.shds.sma.ip.dto.IpAlarmRequestDto;
import com.shds.sma.log.dto.LogAlarmRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {

    private final AlarmRepository alarmRepository;

    private final KakaoAppService kakaoAppService;

    private final MailService mailService;

    private final SmsService smsService;

    @Override
    public void sendLogByKakaoApp(List<LogAlarmRequestDto> logs) {
        kakaoAppService.sendKakaoApp(logs);
    }

    @Override
    public void sendLogByMail(List<LogAlarmRequestDto> logs) {
        mailService.sendLogMail(logs);
    }

    @Override
    public void sendLogBySms(List<LogAlarmRequestDto> logs) {
        smsService.sendSms(logs);
    }

    @Override
    public void sendIpByKakaoApp(List<IpAlarmRequestDto> ips) {
        kakaoAppService.sendIpKakaoApp(ips);
    }

    @Override
    public void sendIpByMail(List<IpAlarmRequestDto> ips) {
        mailService.sendIpMail(ips);
    }

    @Override
    public void sendIpBySms(List<IpAlarmRequestDto> ips) {
        smsService.sendIpSms(ips);
    }

    @Override
    public void sendCertByKakaoApp(List<CertAlarmRequestDto> certs) {
        kakaoAppService.sendCertKakaoApp(certs);
    }

    @Override
    public void sendCertByMail(List<CertAlarmRequestDto> certs) {
        mailService.sendCertMail(certs);
    }

    @Override
    public void sendCertBySms(List<CertAlarmRequestDto> certs) {
        smsService.sendCertSms(certs);
    }


}
