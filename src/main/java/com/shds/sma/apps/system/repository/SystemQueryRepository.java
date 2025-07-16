package com.shds.sma.apps.system.repository;

import com.shds.sma.apps.system.dto.SystemRequestDto;
import com.shds.sma.apps.system.entity.System;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SystemQueryRepository {

    Page<System> findSystemByCond(SystemRequestDto systemRequestDto, Pageable pageable);

}
