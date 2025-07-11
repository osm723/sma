package com.shds.sma.system.repository;

import com.shds.sma.system.entity.System;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemRepository extends JpaRepository<System, Long>, SystemQueryRepository {

    System findBySystemName(String systemName);
}
