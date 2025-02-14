package com.shds.sma.common.log.repository;

import com.shds.sma.common.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long>, LogQueryRepository {
}
