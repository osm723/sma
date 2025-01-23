package com.shds.sma.home.service;

import com.shds.sma.admin.dto.NoticeHomeResponseDto;
import com.shds.sma.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final AdminService adminService;

    @Override
    public Page<NoticeHomeResponseDto> findHomeNotice(Pageable pageable) {
        return adminService.findHomeNotice(pageable);
    }
}
