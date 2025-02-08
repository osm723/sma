package com.shds.sma.common.entity;

import com.shds.sma.common.types.ApprovalStatus;
import com.shds.sma.common.types.Degree;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private ApprovalStatus status;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime approveDate;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime cancelDate;

}
