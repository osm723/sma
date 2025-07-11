package com.shds.sma.system.repository;

import com.shds.sma.system.dto.SystemRequestDto;
import com.shds.sma.system.entity.System;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SystemQueryRepository {

    Page<System> findSystemByCond(SystemRequestDto systemRequestDto, Pageable pageable);

}
