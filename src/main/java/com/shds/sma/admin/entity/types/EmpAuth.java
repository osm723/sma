package com.shds.sma.admin.entity.types;

import lombok.Getter;

@Getter
public enum EmpAuth {
    NORMAL("일반"),
    MANAGER("담당자"),
    HEADER("부서장"),
    ADMIN("관리자"),
    TEMP("임시")
    ;

    private final String empAuth;

    EmpAuth(String empAuth) {
        this.empAuth = empAuth;
    }
}
