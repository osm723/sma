package com.shds.sma.log.repository;

import com.shds.sma.log.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long>, LogQueryRepository {
}
