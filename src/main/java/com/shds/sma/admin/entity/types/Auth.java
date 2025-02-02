package com.shds.sma.admin.entity.types;

public enum Auth {
    NORMAL("일반"),
    MANAGER("담당자"),
    HEADER("부서장"),
    ADMIN("관리자");


    private final String systemAuth;

    Auth(String systemAuth) {
        this.systemAuth = systemAuth;
    }

    public String getSystemAuth() {
        return systemAuth;
    }
}
