package com.shds.sma.apps.home.service;

import com.shds.sma.apps.admin.notice.dto.NoticeHomeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HomeService {

    Page<NoticeHomeResponseDto> findHomeNotice(Pageable pageable);
}
