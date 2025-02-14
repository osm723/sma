package com.shds.sma.alarm.repository;

import com.shds.sma.alarm.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long>, AlarmQueryRepository {
}
