package com.shds.sma.common.entity;


import com.shds.sma.common.types.ValidityStatus;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static com.shds.sma.common.types.ValidityStatus.*;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime regDate;

    private Long regMemberId;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime modDate;

    private Long modMemberId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ValidityStatus validity = Y;

    /**
     * 삭제 처리 (미사용 처리)
     * validity = N
     */
    public void setValidityN() {
        this.validity = N;
    }

    /**
     * 복구 처리 (사용 처리)
     * validity = Y
     */
    public void setValidityY() {
        this.validity = Y;
    }

}
