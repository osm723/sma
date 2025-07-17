package com.shds.sma.apps.admin.notice.service;

import com.shds.sma.apps.admin.notice.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeAdminService {

    Page<NoticeResponseDto> findNoticeAll(Pageable pageable);

    Page<NoticeResponseDto> findNoticeByCond(NoticeCondRequestDto noticeCondRequestDto, Pageable pageable);

    Page<NoticeHomeResponseDto> findHomeNotice(Pageable pageable);

    NoticeResponseDto findNoticeById(Long noticeId);

    void saveNotice(NoticeSaveRequestDto noticeSaveRequestDto);

    void modifiedNotice(NoticeModRequestDto noticeModRequestDto);

    void removeNotice(Long noticeId);

    void useNotice(Long noticeId);
}
