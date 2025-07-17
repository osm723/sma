package com.shds.sma.apps.cert.service;

import com.shds.sma.apps.alarm.service.AlarmService;
import com.shds.sma.apps.alarm.types.AlarmSendType;
import com.shds.sma.apps.cert.dto.CertAlarmRequestDto;
import com.shds.sma.apps.cert.dto.CertRequestDto;
import com.shds.sma.apps.cert.dto.CertResponseDto;
import com.shds.sma.apps.cert.entity.Cert;
import com.shds.sma.apps.cert.repository.CertRepository;
import com.shds.sma.common.exception.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.shds.sma.common.exception.ExceptionMessageConst.NOT_FOUND_CERT;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CertServiceImpl implements CertService {

    private final CertRepository certRepository;

    private final AlarmService alarmService;

    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<CertResponseDto> findCertByCond(CertRequestDto certRequestDto, Pageable pageable) {
        Page<Cert> findCert = certRepository.findCertByCond(certRequestDto, pageable);
        return findCert.map(CertResponseDto::new);
    }

    @Override
    @Transactional(readOnly = true)
    public CertResponseDto findCertById(Long certId) {
        Cert findCert = certRepository.findById(certId).orElseThrow(() -> new BizException(NOT_FOUND_CERT));
        return modelMapper.map(findCert, CertResponseDto.class);
    }

    @Override
    public void getCertPreExpiration() {
        List<Cert> preCertExpiration = certRepository.findCertPreExpiration();
        for (Cert cert : preCertExpiration) {
            if(isNotCertReApply(cert)) {
                sendAlarm(preCertExpiration, List.of(AlarmSendType.KAKAO, AlarmSendType.MAIL, AlarmSendType.SMS));
            }
        }
    }

    @Override
    public void getCertPreExpirationToManager() {
        Long preDay = 1L;
        List<Cert> preIpExpiration = certRepository.findCertPreDayExpiration(preDay);
        for (Cert cert : preIpExpiration) {
            if(isNotCertReApply(cert)) {
                sendAlarmToManager(preIpExpiration, List.of(AlarmSendType.KAKAO, AlarmSendType.MAIL, AlarmSendType.SMS));
            }
        }
    }

    private boolean isNotCertReApply(Cert cert) {
        return !certRepository.isCertReApply(cert);
    }

    /**
     * 알림 발송
     * sendAlarm
     * @param preCertExpiration
     * @param alarmSendTypes
     */
    private void sendAlarm(List<Cert> preCertExpiration, List<AlarmSendType> alarmSendTypes) {
        List<CertAlarmRequestDto> certs = preCertExpiration.stream().map(CertAlarmRequestDto::new).collect(Collectors.toList());

        for (AlarmSendType alarmSendType : alarmSendTypes) {
            switch (alarmSendType) {
                case SMS -> alarmService.sendCertBySms(certs);
                case MAIL -> alarmService.sendCertByMail(certs);
                case KAKAO -> alarmService.sendCertByKakaoApp(certs);
            }
        }
    }

    /**
     * 알림 발송
     * sendAlarm
     * @param preCertExpiration
     * @param alarmSendTypes
     */
    private void sendAlarmToManager(List<Cert> preCertExpiration, List<AlarmSendType> alarmSendTypes) {
        List<CertAlarmRequestDto> certs = preCertExpiration.stream().map(CertAlarmRequestDto::new).collect(Collectors.toList());

        for (AlarmSendType alarmSendType : alarmSendTypes) {
            switch (alarmSendType) {
                case SMS -> alarmService.sendCertToManagerBySms(certs);
                case MAIL -> alarmService.sendCertToManagerByMail(certs);
                case KAKAO -> alarmService.sendCertToManagerByKakaoApp(certs);
            }
        }
    }


}
