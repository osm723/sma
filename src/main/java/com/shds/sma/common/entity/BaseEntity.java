package com.shds.sma.common.entity;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @Column(length = 14, columnDefinition = "VARCHAR(14) COMMENT '등록일시'")
    private LocalDateTime regDate;

    @Column(length = 40, columnDefinition = "VARCHAR(40) COMMENT '등록자'")
    private String regMemberId;

    @Column(length = 14, columnDefinition = "VARCHAR(14) COMMENT '수정일시'")
    private LocalDateTime modDate;

    @Column(length = 40, columnDefinition = "VARCHAR(40) COMMENT '수정자'")
    private String modMemberId;

    @Column(length = 1, columnDefinition = "VARCHAR(1) COMMENT '사용여부'")
    private String validity;


    /**
     * 삭제 처리 (미사용 처리)
     * validity = N
     */
    public void baseDelete() {
        this.validity = "N";
    }

    /**
     * 복구 처리 (사용 처리)
     * validity = Y
     */
    public void baseUse() {
        this.validity = "Y";
    }

}
