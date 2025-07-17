package com.shds.sma.external.api.dto.common;


import com.shds.sma.apps.admin.client.entity.Client;
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
        if (client != null) {
            this.clientCode = client.getClientCode();
            this.clientName = client.getClientName();
        }
    }
}
