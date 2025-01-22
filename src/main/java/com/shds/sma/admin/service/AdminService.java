package com.shds.sma.admin.service;

import com.shds.sma.admin.dto.NoticeModRequestDto;
import com.shds.sma.admin.dto.NoticeRemoveRequestDto;
import com.shds.sma.admin.dto.NoticeResponseDto;
import com.shds.sma.admin.dto.NoticeSaveRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {

    Page<NoticeResponseDto> findNoticeAll(Pageable pageable);

    void findNoticeCond();

    NoticeResponseDto findNoticeById(Long noticeId);

    void noticeSave(NoticeSaveRequestDto noticeSaveRequestDto);

    void noticeModified(NoticeModRequestDto noticeModRequestDto);

    void noticeRemove(NoticeRemoveRequestDto noticeRemoveRequestDto);


}
