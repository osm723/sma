package com.shds.sma.common.entity;

import com.shds.sma.common.types.ApprovalStatus;
import com.shds.sma.common.types.Degree;
import com.shds.sma.ip.dto.IpModRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "SMA_APPROVAL")
@Getter
@NoArgsConstructor
public class Approval {

    @Id
    @GeneratedValue
    @Column(name = "APPROVAL_ID")
    private Long id;

    @Column(length = 40, columnDefinition = "VARCHAR(40) COMMENT '결재번호'", nullable = false)
    @NotBlank
    private String approvalNo;

    @Column(columnDefinition = "BIGINT COMMENT '기안자Id'", nullable = false)
    @NotNull
    private Long drafterId;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '결재차수'", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Degree degree;

    @Column(columnDefinition = "BIGINT COMMENT '결재자Id'", nullable = false)
    @NotNull
    private Long approverId;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT '결재상태'", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime approveDate;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime cancelDate;

    @Builder
    public Approval(String approvalNo, Long drafterId, Degree degree, Long approverId, ApprovalStatus approvalStatus, LocalDateTime approveDate, LocalDateTime cancelDate) {
        this.approvalNo = approvalNo;
        this.drafterId = drafterId;
        this.degree = degree;
        this.approverId = approverId;
        this.approvalStatus = approvalStatus;
        this.approveDate = approveDate;
        this.cancelDate = cancelDate;
    }

    public void approvalModified(IpModRequestDto ipModRequestDto) {
        this.approvalNo = ipModRequestDto.getApprovalNo();
        this.drafterId = ipModRequestDto.getDrafterId();
        this.degree = ipModRequestDto.getDegree();
        this.approverId = ipModRequestDto.getApproverId();
        this.approvalStatus = ipModRequestDto.getApprovalStatus();
        this.approveDate = ipModRequestDto.getApproveDate();
        this.cancelDate = ipModRequestDto.getCancelDate();
    }
}
