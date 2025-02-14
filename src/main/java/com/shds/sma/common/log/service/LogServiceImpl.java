package com.shds.sma.common.log.service;

import com.shds.sma.common.entity.Log;
import com.shds.sma.common.log.dto.LogErrorResponseDto;
import com.shds.sma.common.log.dto.LogRequestDto;
import com.shds.sma.common.log.repository.LogRepository;
import jakarta.persistence.EntityManager;
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

    private final ModelMapper modelMapper;

    private static final Long TEN_MINUTE = 10L;
    private static final Long ONE_DAY = 1L;


    @Override
    public void saveLog(LogRequestDto logRequestDto) {
        logRepository.save(modelMapper.map(logRequestDto, Log.class));
    }

    @Override
    public List<LogErrorResponseDto> getLogErrorForTenMin() {
        List<Log> findLog = logRepository.findLogForMin(TEN_MINUTE);
        return findLog.stream().map(LogErrorResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public List<LogErrorResponseDto> getLogErrorForDaily() {
        List<Log> findLog = logRepository.findLogForDaily(ONE_DAY);
        return findLog.stream().map(LogErrorResponseDto::new).collect(Collectors.toList());
    }
}
