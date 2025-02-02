package com.shds.sma.admin.repositroy.system;

import com.shds.sma.admin.dto.system.SystemRequestDto;
import com.shds.sma.admin.entity.System;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SystemQueryRepository {

    Page<System> findSystemByCond(SystemRequestDto systemRequestDto, Pageable pageable);

}
