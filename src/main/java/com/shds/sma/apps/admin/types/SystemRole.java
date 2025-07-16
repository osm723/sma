package com.shds.sma.apps.admin.types;

import lombok.Getter;

@Getter
public enum SystemRole {
    NORMAL("일반"),
//    MANAGER("담당자"),
    HEADER("부서장"),
    DEVELOPER("개발자"),
    INFRA("인프라"),
    SECURITY("보안"),
    OPERATION("운영"),
    EXTERNAL("외부"),
    QA("품질"),
    TEMP("임시")
    ;

    private final String description;

    SystemRole(String description) {
        this.description = description;
    }
}
