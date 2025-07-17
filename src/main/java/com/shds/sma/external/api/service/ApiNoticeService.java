package com.shds.sma.external.api.service;

import com.shds.sma.external.api.dto.notice.ApiNoticeModRequestDto;
import com.shds.sma.external.api.dto.notice.ApiNoticeResponseDto;
import com.shds.sma.external.api.dto.notice.ApiNoticeSaveRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApiNoticeService {
    Page<ApiNoticeResponseDto> getAllNotices(Pageable pageable);

    ApiNoticeResponseDto getNotice(Long noticeId);

    ApiNoticeResponseDto createNotice(ApiNoticeSaveRequestDto apiNoticeSaveRequestDto);

    ApiNoticeResponseDto updateNotice(ApiNoticeModRequestDto apiNoticeModRequestDto);

    void deleteNotice(Long noticeId);

    void reuseNotice(Long noticeId);
}
