package com.shds.sma.common.types;

import lombok.Getter;

@Getter
public enum Degree {
    FIRST("1차"),
    SECOND("2차"),
    THIRD("3차"),
    FOURTH("4차"),
    FINAL("최종");

    private final String description;

    Degree(String description) {
        this.description = description;
    }
}
