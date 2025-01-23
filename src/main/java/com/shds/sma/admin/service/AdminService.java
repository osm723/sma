package com.shds.sma.admin.service;

import com.shds.sma.admin.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {

    Page<NoticeResponseDto> findNoticeAll(Pageable pageable);

    Page<NoticeResponseDto> findNoticeCond(NoticeCondRequestDto noticeCondRequestDto, Pageable pageable);

    Page<NoticeHomeResponseDto> findHomeNotice(Pageable pageable);

    NoticeResponseDto findNoticeById(Long noticeId);

    void noticeSave(NoticeSaveRequestDto noticeSaveRequestDto);

    void noticeModified(NoticeModRequestDto noticeModRequestDto);

    void noticeRemove(Long noticeId);

    void noticeUse(Long noticeId);
}
