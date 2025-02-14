package com.shds.sma.log.repository;

import com.shds.sma.log.entity.Log;

import java.util.List;

public interface LogQueryRepository {
    List<Log> findLogForMin(Long min);

    List<Log> findLogForDaily(Long day);
}
