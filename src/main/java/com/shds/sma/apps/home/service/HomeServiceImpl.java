package com.shds.sma.apps.home.service;

import com.shds.sma.apps.admin.dto.notice.NoticeHomeResponseDto;
import com.shds.sma.apps.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final AdminService adminService;

    @Override
    public Page<NoticeHomeResponseDto> findHomeNotice(Pageable pageable) {
        return adminService.findHomeNotice(pageable);
    }
}
