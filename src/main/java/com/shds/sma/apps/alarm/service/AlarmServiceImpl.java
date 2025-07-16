package com.shds.sma.apps.alarm.service;

import com.shds.sma.apps.alarm.dto.AlarmRequestDto;
import com.shds.sma.apps.alarm.entity.Alarm;
import com.shds.sma.apps.alarm.service.kakao.KakaoAppService;
import com.shds.sma.apps.alarm.service.mail.MailService;
import com.shds.sma.apps.alarm.service.sms.SmsService;
import com.shds.sma.apps.alarm.repository.AlarmRepository;
import com.shds.sma.apps.cert.dto.CertAlarmRequestDto;
import com.shds.sma.apps.ip.dto.IpAlarmRequestDto;
import com.shds.sma.apps.log.dto.LogAlarmRequestDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

    private final ModelMapper modelMapper;

    @Override
    public void sendLogByKakaoApp(List<LogAlarmRequestDto> logs) {
        AlarmRequestDto alarmRequestDto = kakaoAppService.sendKakaoApp(logs);
        // 알림 저장
        alarmRepository.save(modelMapper.map(alarmRequestDto, Alarm.class));
    }

    @Override
    public void sendLogByMail(List<LogAlarmRequestDto> logs) {
        AlarmRequestDto alarmRequestDto = mailService.sendLogMail(logs);
        // 알림 저장
        alarmRepository.save(modelMapper.map(alarmRequestDto, Alarm.class));
    }

    @Override
    public void sendLogBySms(List<LogAlarmRequestDto> logs) {
        AlarmRequestDto alarmRequestDto = smsService.sendSms(logs);
        // 알림 저장
        alarmRepository.save(modelMapper.map(alarmRequestDto, Alarm.class));
    }

    @Override
    public void sendIpByKakaoApp(List<IpAlarmRequestDto> ips) {
        List<AlarmRequestDto> alarmRequests = kakaoAppService.sendIpKakaoApp(ips);
        // 알림 저장
        saveAlarm(alarmRequests);
    }

    @Override
    public void sendIpByMail(List<IpAlarmRequestDto> ips) {
        List<AlarmRequestDto> alarmRequests = mailService.sendIpMail(ips);
        // 알림 저장
        saveAlarm(alarmRequests);
    }

    @Override
    public void sendIpBySms(List<IpAlarmRequestDto> ips) {
        List<AlarmRequestDto> alarmRequests = smsService.sendIpSms(ips);
        // 알림 저장
        saveAlarm(alarmRequests);
    }

    @Override
    public void sendIpToManagerByKakaoApp(List<IpAlarmRequestDto> ips) {
        List<AlarmRequestDto> alarmRequests = kakaoAppService.sendIpToManagerKakaoApp(ips);
        // 알림 저장
        saveAlarm(alarmRequests);
    }

    @Override
    public void sendIpToManagerByMail(List<IpAlarmRequestDto> ips) {
        List<AlarmRequestDto> alarmRequests = mailService.sendIpToManagerMail(ips);
        // 알림 저장
        saveAlarm(alarmRequests);
    }

    @Override
    public void sendIpToManagerBySms(List<IpAlarmRequestDto> ips) {
        List<AlarmRequestDto> alarmRequests = smsService.sendIpToManagerSms(ips);
        // 알림 저장
        saveAlarm(alarmRequests);
    }

    @Override
    public void sendCertByKakaoApp(List<CertAlarmRequestDto> certs) {
        List<AlarmRequestDto> alarmRequests = kakaoAppService.sendCertKakaoApp(certs);
        // 알림 저장
        saveAlarm(alarmRequests);
    }

    @Override
    public void sendCertByMail(List<CertAlarmRequestDto> certs) {
        List<AlarmRequestDto> alarmRequests = mailService.sendCertMail(certs);
        // 알림 저장
        saveAlarm(alarmRequests);
    }

    @Override
    public void sendCertBySms(List<CertAlarmRequestDto> certs) {
        List<AlarmRequestDto> alarmRequests = smsService.sendCertSms(certs);
        // 알림 저장
        saveAlarm(alarmRequests);
    }

    @Override
    public void sendCertToManagerByKakaoApp(List<CertAlarmRequestDto> certs) {
        List<AlarmRequestDto> alarmRequests = kakaoAppService.sendCertToManagerKakaoApp(certs);
        // 알림 저장
        saveAlarm(alarmRequests);
    }

    @Override
    public void sendCertToManagerByMail(List<CertAlarmRequestDto> certs) {
        List<AlarmRequestDto> alarmRequests = mailService.sendCertToManagerMail(certs);
        // 알림 저장
        saveAlarm(alarmRequests);
    }

    @Override
    public void sendCertToManagerBySms(List<CertAlarmRequestDto> certs) {
        List<AlarmRequestDto> alarmRequests = smsService.sendCertToManagerSms(certs);
        // 알림 저장
        saveAlarm(alarmRequests);
    }

    /**
     * 알림 저장
     * saveAlarm
     * @param alarmRequests
     */
    private void saveAlarm(List<AlarmRequestDto> alarmRequests) {
        for (AlarmRequestDto alarmRequest : alarmRequests) {
            alarmRepository.save(modelMapper.map(alarmRequest, Alarm.class));
        }
    }


}
