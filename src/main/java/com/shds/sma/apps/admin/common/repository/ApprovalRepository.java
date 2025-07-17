package com.shds.sma.apps.admin.common.repository;

import com.shds.sma.apps.admin.common.entity.Approval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalRepository extends JpaRepository<Approval, Long> {
}
