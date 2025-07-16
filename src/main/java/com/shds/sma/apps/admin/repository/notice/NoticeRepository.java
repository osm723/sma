package com.shds.sma.apps.admin.repository.notice;

import com.shds.sma.apps.admin.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeQueryRepository {

}
