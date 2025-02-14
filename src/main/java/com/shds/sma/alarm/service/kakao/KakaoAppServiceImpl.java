package com.shds.sma.alarm.service.kakao;

import com.shds.sma.log.dto.LogErrorResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class KakaoAppServiceImpl implements KakaoAppService {

    @Override
    public void sendKakaoApp(List<LogErrorResponseDto> errorLogs) {

    }
}
