package com.shds.sma.common.alarm.service;

import com.shds.sma.common.alarm.kakao.KakaoAppService;
import com.shds.sma.common.alarm.mail.MailService;
import com.shds.sma.common.alarm.repository.AlarmRepository;
import com.shds.sma.common.alarm.sms.SmsService;
import com.shds.sma.common.log.dto.LogErrorResponseDto;
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
