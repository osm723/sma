package com.shds.sma.common.entity;

import com.shds.sma.api.dto.cert.ApiCertModRequestDto;
import com.shds.sma.api.dto.common.ApiApproval;
import com.shds.sma.cert.dto.CertModRequestDto;
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

    public Approval(ApiApproval apiApproval) {
        this.approvalNo = apiApproval.getApprovalNo();
        this.drafterId = apiApproval.getDrafterId();
        this.degree = apiApproval.getDegree();
        this.approverId = apiApproval.getApproverId();
        this.approvalStatus = apiApproval.getApprovalStatus();
        this.approveDate = apiApproval.getApproveDate();
        this.cancelDate = apiApproval.getCancelDate();
    }

    public void approvalIpModified(IpModRequestDto ipModRequestDto) {
        this.approvalNo = ipModRequestDto.getApprovalNo();
        this.drafterId = ipModRequestDto.getDrafterId();
        this.degree = ipModRequestDto.getDegree();
        this.approverId = ipModRequestDto.getApproverId();
        this.approvalStatus = ipModRequestDto.getApprovalStatus();
        this.approveDate = ipModRequestDto.getApproveDate();
        this.cancelDate = ipModRequestDto.getCancelDate();
    }

    public void approvalCertModified(CertModRequestDto certModRequestDto) {
        this.approvalNo = certModRequestDto.getApprovalNo();
        this.drafterId = certModRequestDto.getDrafterId();
        this.degree = certModRequestDto.getDegree();
        this.approverId = certModRequestDto.getApproverId();
        this.approvalStatus = certModRequestDto.getApprovalStatus();
        this.approveDate = certModRequestDto.getApproveDate();
        this.cancelDate = certModRequestDto.getCancelDate();
    }

    public void approvalApiCertModified(ApiApproval apiApproval) {
        this.approvalNo = apiApproval.getApprovalNo();
        this.drafterId = apiApproval.getDrafterId();
        this.degree = apiApproval.getDegree();
        this.approverId = apiApproval.getApproverId();
        this.approvalStatus = apiApproval.getApprovalStatus();
        this.approveDate = apiApproval.getApproveDate();
        this.cancelDate = apiApproval.getCancelDate();
    }


}
