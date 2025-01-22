package com.shds.sma.common.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    @CreatedDate
    private LocalDateTime regDate;

    @Column(columnDefinition = "BIGINT COMMENT '등록자'")
    private Long regMemberId;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    @LastModifiedDate
    private LocalDateTime modDate;

    @Column(columnDefinition = "BIGINT COMMENT '수정자'")
    private Long modMemberId;

    @Column(length = 1, columnDefinition = "VARCHAR(1) COMMENT '사용여부'", nullable = false)
    @NotBlank
    private String validity = "Y";


    /**
     * 삭제 처리 (미사용 처리)
     * validity = N
     */
    public void setValidityN() {
        this.validity = "N";
    }

    /**
     * 복구 처리 (사용 처리)
     * validity = Y
     */
    public void setValidityY() {
        this.validity = "Y";
    }

}
