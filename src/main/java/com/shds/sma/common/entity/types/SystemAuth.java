package com.shds.sma.common.entity.types;

public enum SystemAuth {
    NORMAL("일반"),
    MANAGER("담당자"),
    HEADER("부서장"),
    ADMIN("관리자");


    private final String systemAuth;

    SystemAuth(String systemAuth) {
        this.systemAuth = systemAuth;
    }

    public String getSystemAuth() {
        return systemAuth;
    }
}
