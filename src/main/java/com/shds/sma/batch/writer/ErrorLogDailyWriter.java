package com.shds.sma.batch.writer;

import com.shds.sma.apps.alarm.service.AlarmService;
import com.shds.sma.apps.alarm.types.AlarmSendType;
import com.shds.sma.apps.log.dto.LogAlarmRequestDto;
import com.shds.sma.apps.log.entity.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ErrorLogDailyWriter implements ItemWriter<Log> {

    private final AlarmService alarmService;

    @Override
    public void write(Chunk<? extends Log> chunk) throws Exception {
        List<Log> logs = (List<Log>) chunk.getItems();
        sendAlarm(logs, List.of(AlarmSendType.MAIL));

    }

    /**
     * 알림 발송
     * sendAlarm
     *
     * @param findLog
     */
    private void sendAlarm(List<Log> findLog, List<AlarmSendType> alarmSendTypes) {
        List<LogAlarmRequestDto> errors = findLog.stream().map(LogAlarmRequestDto::new).collect(Collectors.toList());
        for (AlarmSendType alarmSendType : alarmSendTypes) {
            switch (alarmSendType) {
                case SMS -> alarmService.sendLogByKakaoApp(errors);
                case MAIL -> alarmService.sendLogByMail(errors);
                case KAKAO -> alarmService.sendLogBySms(errors);
            }
        }
    }

}
