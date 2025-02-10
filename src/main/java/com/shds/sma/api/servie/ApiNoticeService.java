package com.shds.sma.api.servie;

import com.shds.sma.api.dto.notice.ApiNoticeModRequestDto;
import com.shds.sma.api.dto.notice.ApiNoticeResponseDto;
import com.shds.sma.api.dto.notice.ApiNoticeSaveRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApiNoticeService {
    Page<ApiNoticeResponseDto> getAllNotices(Pageable pageable);

    ApiNoticeResponseDto getNotice(Long noticeId);

    ApiNoticeResponseDto createNotice(ApiNoticeSaveRequestDto apiMemberSaveRequestDto);

    ApiNoticeResponseDto updateNotice(ApiNoticeModRequestDto apiMemberModRequestDto);

    void deleteNotice(Long noticeId);

    void reuseNotice(Long noticeId);
}
