package com.shds.sma.common.types;

import lombok.Getter;

@Getter
public enum ApprovalStatus {
    PROGRESS("진행중"),
    CANCEL("반려"),
    APPROVE("승인"),
    HOLDING("중지"),
    DELAY("지연"),
    POST_APPROVE("후결재"),
    PRE_APPROVE("선결재"),
    ;

    private final String description;

    ApprovalStatus(String description) {
        this.description = description;
    }
}
