package com.shds.sma.api.dto.common;


import com.shds.sma.admin.entity.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiClient {

    private String clientCode;

    private String clientName;

    public ApiClient(Client client) {
        this.clientCode = client.getClientCode();
        this.clientName = client.getClientName();
    }
}
