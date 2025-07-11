package com.shds.sma.admin.entity;

import com.shds.sma.admin.dto.client.ClientModRequestDto;
import com.shds.sma.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SMA_CLIENT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Client extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "CLIENT_ID")
    private Long id;

    @Column(length = 4, columnDefinition = "VARCHAR(4) COMMENT '고객사코드'", nullable = false)
    private String clientCode;

    @Column(length = 60, columnDefinition = "VARCHAR(60) COMMENT '고객사명'", nullable = false)
    private String clientName;

    public void clientModified(ClientModRequestDto clientModRequestDto) {
        this.clientCode = clientModRequestDto.getClientCode();
        this.clientName = clientModRequestDto.getClientName();
    }

}

