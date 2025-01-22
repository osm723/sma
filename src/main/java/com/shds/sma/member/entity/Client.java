package com.shds.sma.member.entity;

import com.shds.sma.common.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "SMA_CLIENT")
public class Client extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "CLIENT_ID")
    private Long id;

    @Column(length = 4, columnDefinition = "VARCHAR(4) COMMENT '고객사코드'", nullable = false)
    @NotBlank
    private String clientCode;

    @Column(length = 60, columnDefinition = "VARCHAR(60) COMMENT '고객사명'", nullable = false)
    @NotBlank
    private String clientName;

}

