package com.shds.sma.log.service;

import com.shds.sma.log.dto.LogRequestDto;

public interface LogService {

    void saveLog(LogRequestDto logRequestDto);

    void getLogErrorForTenMin();

    void getLogErrorForDaily();
}
