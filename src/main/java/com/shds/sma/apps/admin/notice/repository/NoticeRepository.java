package com.shds.sma.apps.admin.notice.repository;

import com.shds.sma.apps.admin.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeQueryRepository {

}
