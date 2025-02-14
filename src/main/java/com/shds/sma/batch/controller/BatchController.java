package com.shds.sma.batch.controller;


import com.shds.sma.cert.service.CertService;
import com.shds.sma.common.alarm.service.AlarmService;
import com.shds.sma.log.service.LogService;
import com.shds.sma.ip.service.IpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BatchController {

    private final LogService logService;

    private final IpService ipService;

    private final CertService certService;

    private final AlarmService alarmService;

    /**
     * 10분마다 에러로그를 조회해서 5건 이상이면 알림 발송
     * alarmErrorLogForTenMin
     */
    @Scheduled(cron = "0 */10 * * * *")
    //@Scheduled(fixedRate = 600000)
    public void alarmErrorLogForTenMin() {
        logService.getLogErrorForTenMin();
    }

    /**
     * 하루동안 에러로그를 조회해서 알림 발송
     * alarmErrorLogForDaily
     */
    @Scheduled(cron = "0 0 8 * * *")
    public void alarmErrorLogForDaily() {
        logService.getLogErrorForDaily();
    }

    /**
     * Ip 만료도래시 알림 발송
     * alarmPreExpiration
     */
    @Scheduled(cron = "0 0 10,16 * * *")
    public void alarmIpPreExpiration() {
        ipService.getPreExpiration();
    }



}
