package com.shds.sma.common.types;

import lombok.Getter;

@Getter
public enum ValidityStatus {
    Y("사용"),
    N("미사용");

    private final String description;

    ValidityStatus(String description) {
        this.description = description;
    }
}
