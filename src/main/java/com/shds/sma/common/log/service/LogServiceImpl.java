package com.shds.sma.common.log.service;

import com.shds.sma.common.entity.Log;
import com.shds.sma.common.log.dto.LogRequestDto;
import com.shds.sma.common.log.repository.LogRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    private final ModelMapper modelMapper;

    @Override
    public void saveLog(LogRequestDto logRequestDto) {
        logRepository.save(modelMapper.map(logRequestDto, Log.class));
    }
}
