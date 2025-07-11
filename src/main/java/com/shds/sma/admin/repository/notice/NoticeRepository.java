package com.shds.sma.admin.repository.notice;

import com.shds.sma.admin.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeQueryRepository {

}
