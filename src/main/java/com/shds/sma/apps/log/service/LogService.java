package com.shds.sma.apps.log.service;

import com.shds.sma.apps.log.dto.LogRequestDto;

public interface LogService {

    void saveLog(LogRequestDto logRequestDto);

    void getLogErrorForTenMin();

    void getLogErrorForDaily();
}
