package com.shds.sma.external.api.dto.common;

import com.shds.sma.apps.admin.entity.Approval;
import com.shds.sma.common.types.ApprovalStatus;
import com.shds.sma.common.types.Degree;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ApiApproval {

    private Long approvalId;

    private String approvalNo;

    private Long drafterId;

    private Degree degree;

    private Long approverId;

    private ApprovalStatus approvalStatus;

    private LocalDateTime approveDate;

    private LocalDateTime cancelDate;

    public ApiApproval(Approval approval) {
        if (approval != null) {
            this.approvalId = approval.getId();
            this.approvalNo = approval.getApprovalNo();
            this.drafterId = approval.getDrafterId();
            this.degree = approval.getDegree();
            this.approverId = approval.getApproverId();
            this.approvalStatus = approval.getApprovalStatus();
            this.approveDate = approval.getApproveDate();
            this.cancelDate = approval.getCancelDate();
        }
    }
}
