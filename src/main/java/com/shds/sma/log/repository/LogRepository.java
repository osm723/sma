package com.shds.sma.log.repository;

import com.shds.sma.log.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

public interface LogRepository extends JpaRepository<Log, Long>, LogQueryRepository {

    Page<Log> findByLogDateAfter(LocalDateTime currentDate, Pageable pageable);
}
