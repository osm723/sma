package com.shds.sma.apps.log.repository;

import com.shds.sma.apps.log.entity.Log;

import java.util.List;

public interface LogQueryRepository {
    List<Log> findLogForMin(Long min);

    List<Log> findLogForDaily(Long day);
}
