package com.shds.sma.common.alarm.repository;

import com.shds.sma.common.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long>, AlarmQueryRepository {
}
