package com.shds.sma.admin.repositroy.system;

import com.shds.sma.admin.entity.System;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemRepository extends JpaRepository<System, Long>, SystemQueryRepository {

}
