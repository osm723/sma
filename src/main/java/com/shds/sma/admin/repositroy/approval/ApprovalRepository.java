package com.shds.sma.admin.repositroy.approval;

import com.shds.sma.common.entity.Approval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalRepository extends JpaRepository<Approval, Long> {
}
