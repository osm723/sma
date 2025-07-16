package com.shds.sma.apps.home.service;

import com.shds.sma.apps.admin.dto.notice.NoticeHomeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HomeService {

    Page<NoticeHomeResponseDto> findHomeNotice(Pageable pageable);
}
