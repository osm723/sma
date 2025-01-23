package com.shds.sma.home.service;

import com.shds.sma.admin.dto.HomeNoticeResponseDto;
import com.shds.sma.admin.dto.NoticeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HomeService {

    Page<HomeNoticeResponseDto> findHomeNotice(Pageable pageable);
}
