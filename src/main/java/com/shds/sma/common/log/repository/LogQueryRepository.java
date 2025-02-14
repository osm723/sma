package com.shds.sma.common.log.repository;

import com.shds.sma.common.entity.Log;

import java.util.List;

public interface LogQueryRepository {
    List<Log> findLogForMin(Long min);

    List<Log> findLogForDaily(Long day);
}
