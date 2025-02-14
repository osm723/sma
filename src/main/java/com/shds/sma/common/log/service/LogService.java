package com.shds.sma.common.log.service;

import com.shds.sma.common.log.dto.LogErrorResponseDto;
import com.shds.sma.common.log.dto.LogRequestDto;

import java.util.List;

public interface LogService {

    void saveLog(LogRequestDto logRequestDto);

    void getLogErrorForTenMin();

    void getLogErrorForDaily();
}
