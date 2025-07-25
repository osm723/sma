package com.shds.sma.apps.log.service;

import com.shds.sma.apps.alarm.service.AlarmService;
import com.shds.sma.apps.log.dto.LogAlarmRequestDto;
import com.shds.sma.apps.log.entity.Log;
import com.shds.sma.apps.log.dto.LogRequestDto;
import com.shds.sma.apps.log.repository.LogRepository;
import com.shds.sma.apps.alarm.types.AlarmSendType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    private final AlarmService alarmService;

    private final ModelMapper modelMapper;

    private static final Long TEN_MINUTE = 10L;
    private static final Long ONE_DAY = 1L;


    @Override
    public void saveLog(LogRequestDto logRequestDto) {
        logRepository.save(modelMapper.map(logRequestDto, Log.class));
    }

    @Override
    public void getLogErrorForTenMin() {
        List<Log> findLog = logRepository.findLogForMin(TEN_MINUTE);
        if (findLog.size() >= 5) {
            sendAlarm(findLog, List.of(AlarmSendType.KAKAO, AlarmSendType.SMS, AlarmSendType.MAIL));
        }
    }

    @Override
    public void getLogErrorForDaily() {
        List<Log> findLog = logRepository.findLogForDaily(ONE_DAY);
        sendAlarm(findLog, List.of(AlarmSendType.KAKAO, AlarmSendType.SMS, AlarmSendType.MAIL));
    }

    /**
     * 알림 발송
     * sendAlarm
     * @param findLog
     */
    private void sendAlarm(List<Log> findLog, List<AlarmSendType> alarmSendTypes) {
        List<LogAlarmRequestDto> errors = findLog.stream().map(LogAlarmRequestDto::new).collect(Collectors.toList());
        for (AlarmSendType alarmSendType : alarmSendTypes) {
            switch (alarmSendType) {
                case SMS -> alarmService.sendLogBySms(errors);
                case MAIL -> alarmService.sendLogByMail(errors);
                case KAKAO -> alarmService.sendLogByKakaoApp(errors);
            }
        }

    }
}
