package com.shds.sma.apps.ip.service;

import com.shds.sma.apps.alarm.service.AlarmService;
import com.shds.sma.apps.alarm.types.AlarmSendType;
import com.shds.sma.common.exception.BizException;
import com.shds.sma.apps.ip.dto.IpAlarmRequestDto;
import com.shds.sma.apps.ip.dto.IpRequestDto;
import com.shds.sma.apps.ip.dto.IpResponseDto;
import com.shds.sma.apps.ip.entity.Ip;
import com.shds.sma.apps.ip.repository.IpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.shds.sma.common.exception.ExceptionMessageConst.NOT_FOUND_IP;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class IpServiceImpl implements IpService {

    private final IpRepository ipRepository;

    private final AlarmService alarmService;

    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<IpResponseDto> findIpByCond(IpRequestDto ipRequestDto, Pageable pageable) {
        Page<Ip> findIp = ipRepository.findIpByCond(ipRequestDto, pageable);
        return findIp.map(IpResponseDto::new);
    }

    @Override
    @Transactional(readOnly = true)
    public IpResponseDto findIpById(Long ipId) {
        Ip findIp = ipRepository.findById(ipId).orElseThrow(() -> new BizException(NOT_FOUND_IP));
        return modelMapper.map(findIp, IpResponseDto.class);
    }

    @Override
    public void getIpPreExpiration() {
        List<Ip> preIpExpiration = ipRepository.findIpPreExpiration();
        for (Ip ip : preIpExpiration) {
            if(isNotIpReApply(ip)) {
                sendAlarm(preIpExpiration, List.of(AlarmSendType.KAKAO, AlarmSendType.MAIL, AlarmSendType.SMS));
            }
        }
    }

    @Override
    public void getIpPreExpirationToManager() {
        Long preDay = 1L;
        List<Ip> preIpExpiration = ipRepository.findIpPreDayExpiration(preDay);
        for (Ip ip : preIpExpiration) {
            if(isNotIpReApply(ip)) {
                sendAlarmToManager(preIpExpiration, List.of(AlarmSendType.KAKAO, AlarmSendType.MAIL, AlarmSendType.SMS));
            }
        }
    }

    private boolean isNotIpReApply(Ip ip) {
        return !ipRepository.isIpReApply(ip);
    }

    /**
     * 적용대상자에게 알림 발송
     * sendAlarm
     * @param preIpExpiration
     * @param alarmSendTypes
     */
    private void sendAlarm(List<Ip> preIpExpiration, List<AlarmSendType> alarmSendTypes) {
        List<IpAlarmRequestDto> ips = preIpExpiration.stream().map(IpAlarmRequestDto::new).collect(Collectors.toList());

        for (AlarmSendType alarmSendType : alarmSendTypes) {
            switch (alarmSendType) {
                case SMS -> alarmService.sendIpBySms(ips);
                case MAIL -> alarmService.sendIpByMail(ips);
                case KAKAO -> alarmService.sendIpByKakaoApp(ips);
            }
        }
    }

    /**
     * 담당자들에게 알림 발송
     * sendAlarmToManager
     * @param preIpExpiration
     * @param alarmSendTypes
     */
    private void sendAlarmToManager(List<Ip> preIpExpiration, List<AlarmSendType> alarmSendTypes) {
        List<IpAlarmRequestDto> ips = preIpExpiration.stream().map(IpAlarmRequestDto::new).collect(Collectors.toList());

        for (AlarmSendType alarmSendType : alarmSendTypes) {
            switch (alarmSendType) {
                case SMS -> alarmService.sendIpToManagerBySms(ips);
                case MAIL -> alarmService.sendIpToManagerByMail(ips);
                case KAKAO -> alarmService.sendIpToManagerByKakaoApp(ips);
            }
        }
    }


}
