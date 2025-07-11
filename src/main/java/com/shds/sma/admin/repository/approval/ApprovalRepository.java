package com.shds.sma.admin.repository.approval;

import com.shds.sma.admin.entity.Approval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalRepository extends JpaRepository<Approval, Long> {
}
