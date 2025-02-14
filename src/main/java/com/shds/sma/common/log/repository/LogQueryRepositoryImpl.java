package com.shds.sma.common.log.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shds.sma.common.entity.Log;
import com.shds.sma.common.entity.QLog;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.shds.sma.common.entity.QLog.log;

@Repository
public class LogQueryRepositoryImpl implements LogQueryRepository {

    private final JPAQueryFactory query;

    public LogQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Log> findLogForMin(Long min) {
        return query.select(log)
                .from(log)
                .where(log.logDate.goe(LocalDateTime.now().minusMinutes(min)))
                .fetch();
    }

    @Override
    public List<Log> findLogForDaily(Long day) {
        return query.select(log)
                .from(log)
                .where(log.logDate.goe(LocalDateTime.now().minusDays(day)))
                .fetch();
    }


}
