package com.shds.sma.manage.entity;

import com.shds.sma.manage.types.ApprovalStatus;
import com.shds.sma.manage.types.Degree;
import com.shds.sma.member.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "SMA_APPROVAL")
public class Approval {

    @Id
    @GeneratedValue
    @Column(name = "APPROVAL_ID")
    private Long id;

    @Column(length = 40, columnDefinition = "VARCHAR(40) COMMENT '결재번호'")
    private String approvalNo;

    @Column(length = 40, columnDefinition = "INT COMMENT '기안자Id'")
    private Long drafterId;

    @Column(length = 10, columnDefinition = "VARCHAR(10) COMMENT '결재차수'")
    private Degree degree;

    @Column(length = 40, columnDefinition = "INT COMMENT '결재자Id'")
    private Long approverId;

    @Column(length = 12, columnDefinition = "VARCHAR(12) COMMENT '결재상테'")
    private ApprovalStatus status;

    @Column(length = 14, columnDefinition = "VARCHAR(12) COMMENT '승인일자'")
    private LocalDateTime approveDate;

    @Column(length = 14, columnDefinition = "VARCHAR(12) COMMENT '취소일자'")
    private LocalDateTime cancelDate;

}
