package com.shds.sma.apps.admin.dto.client;

import com.shds.sma.apps.admin.entity.Client;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientResponseDto {

    private Long clientId;

    private String clientCode;

    private String clientName;

    private LocalDateTime modDate;

    private Long modMemberId;

    private String validity;

    public ClientResponseDto(Client client) {
        this.clientId = client.getId();
        this.clientCode = client.getClientCode();
        this.clientName = client.getClientName();
        this.modDate = client.getModDate();
        this.modMemberId = client.getModMemberId();
        this.validity = client.getValidity().name();
    }
}
