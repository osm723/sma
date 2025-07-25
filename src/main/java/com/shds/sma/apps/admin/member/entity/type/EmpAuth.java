package com.shds.sma.apps.admin.member.entity.type;


import lombok.Getter;

@Getter
public enum EmpAuth {
    NORMAL("일반"),
    MANAGER("담당자"),
    ADMIN("관리자"),
    TEMP("임시")
    ;

    private final String description;

    EmpAuth(String description) {
        this.description = description;
    }

}
