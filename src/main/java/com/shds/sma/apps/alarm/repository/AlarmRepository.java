package com.shds.sma.apps.alarm.repository;

import com.shds.sma.apps.alarm.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long>, AlarmQueryRepository {
}
