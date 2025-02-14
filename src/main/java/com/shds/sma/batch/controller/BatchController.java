package com.shds.sma.batch.controller;


import com.shds.sma.common.alarm.AlarmService;
import com.shds.sma.common.alarm.mail.MailService;
import com.shds.sma.common.log.dto.LogErrorResponseDto;
import com.shds.sma.common.log.dto.LogRequestDto;
import com.shds.sma.common.log.service.LogService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BatchController {

    private final LogService logService;

    private final AlarmService alarmService;

    /**
     * 10분마다 에러로그를 조회해서 5건 이상이면 메일 발송
     * sendErrorLogForTenMin
     */
    @Scheduled(cron = "0 */10 * * * *")
    //@Scheduled(fixedRate = 600000)
    public void sendErrorLogForTenMin() {
        List<LogErrorResponseDto> errors = logService.getLogErrorForTenMin();
        if (errors.size() >= 5) {
            alarmService.sendMail(errors);
            alarmService.sendSms(errors);
            alarmService.sendKakaoApp(errors);
        }
    }

    /**
     * 하루동안 에러로그를 조회해서 메일 발송
     * sendErrorLogForDaily
     */
    @Scheduled(cron = "0 0 8 * * *")
    public void sendErrorLogForDaily() {
        List<LogErrorResponseDto> errors = logService.getLogErrorForDaily();
        alarmService.sendMail(errors);
        alarmService.sendSms(errors);
        alarmService.sendKakaoApp(errors);
    }



}
