package com.shds.sma.apps.system.repository;

import com.shds.sma.apps.system.entity.System;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemRepository extends JpaRepository<System, Long>, SystemQueryRepository {

    System findBySystemName(String systemName);
}
