package com.shds.sma.apps.home.service;

import com.shds.sma.apps.admin.notice.dto.NoticeHomeResponseDto;
import com.shds.sma.apps.admin.notice.service.NoticeAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final NoticeAdminService noticeAdminService;

    @Override
    public Page<NoticeHomeResponseDto> findHomeNotice(Pageable pageable) {
        return noticeAdminService.findHomeNotice(pageable);
    }
}
