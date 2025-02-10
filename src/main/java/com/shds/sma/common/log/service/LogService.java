package com.shds.sma.common.log.service;

import com.shds.sma.common.log.dto.LogRequestDto;

public interface LogService {

    void saveLog(LogRequestDto logRequestDto);
}
