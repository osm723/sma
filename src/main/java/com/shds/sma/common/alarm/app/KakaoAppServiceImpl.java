package com.shds.sma.common.alarm.app;

import com.shds.sma.common.log.dto.LogErrorResponseDto;
import org.springframework.context.annotation.Primary;
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
