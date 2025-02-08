package com.shds.sma.ip.types;

import lombok.Getter;

@Getter
public enum IpType {
    IP("IP"), IP_BAND("IP대역");

    private final String description;

    IpType(String description) {
        this.description = description;
    }
}
