package com.shds.sma.apps.admin.repository.approval;

import com.shds.sma.apps.admin.entity.Approval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalRepository extends JpaRepository<Approval, Long> {
}
