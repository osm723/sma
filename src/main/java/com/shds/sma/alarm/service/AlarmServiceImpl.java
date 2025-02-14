package com.shds.sma.alarm.service;

import com.shds.sma.alarm.service.kakao.KakaoAppService;
import com.shds.sma.alarm.service.mail.MailService;
import com.shds.sma.alarm.service.sms.SmsService;
import com.shds.sma.alarm.repository.AlarmRepository;
import com.shds.sma.log.dto.LogErrorResponseDto;
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
    public void sendKakaoApp(List<LogErrorResponseDto> errorLogs) {
        kakaoAppService.sendKakaoApp(errorLogs);
    }

    @Override
    public void sendMail(List<LogErrorResponseDto> errorLogs) {
        mailService.sendMail(errorLogs);
    }

    @Override
    public void sendSms(List<LogErrorResponseDto> errorLogs) {
        smsService.sendSms(errorLogs);
    }



}
