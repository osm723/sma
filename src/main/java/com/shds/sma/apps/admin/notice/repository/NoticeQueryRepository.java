package com.shds.sma.apps.admin.notice.repository;

import com.shds.sma.apps.admin.notice.dto.NoticeCondRequestDto;
import com.shds.sma.apps.admin.notice.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeQueryRepository {

    Page<Notice> findNoticeByCond(NoticeCondRequestDto noticeCondRequestDto, Pageable pageable);

    Page<Notice> findHomeNotice(Pageable pageable);
}
