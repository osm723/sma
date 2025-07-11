package com.shds.sma.admin.repository.notice;

import com.shds.sma.admin.dto.notice.NoticeCondRequestDto;
import com.shds.sma.admin.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeQueryRepository {

    Page<Notice> findNoticeByCond(NoticeCondRequestDto noticeCondRequestDto, Pageable pageable);

    Page<Notice> findHomeNotice(Pageable pageable);
}
