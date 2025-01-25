package com.shds.sma.admin.repositroy.notice;

import com.shds.sma.admin.dto.notice.NoticeCondRequestDto;
import com.shds.sma.admin.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeQueryRepository {

    Page<Notice> findNoticeCond(NoticeCondRequestDto noticeCondRequestDto, Pageable pageable);

    Page<Notice> findHomeNotice(Pageable pageable);
}
