package com.shds.sma.api.dto.common;

import com.shds.sma.common.entity.Approval;
import com.shds.sma.common.types.ApprovalStatus;
import com.shds.sma.common.types.Degree;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ApiApproval extends Approval {

    private Long id;

    private String approvalNo;

    private Long drafterId;

    private Degree degree;

    private Long approverId;

    private ApprovalStatus approvalStatus;

    private LocalDateTime approveDate;

    private LocalDateTime cancelDate;

    public ApiApproval(Approval approval) {
        if (approval != null) {
            this.id = approval.getApproverId();
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
