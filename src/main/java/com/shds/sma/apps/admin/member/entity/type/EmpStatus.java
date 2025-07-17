package com.shds.sma.apps.admin.member.entity.type;

import lombok.Getter;

@Getter
public enum EmpStatus {
    EMPLOYEE("재직"),
    RETIRE("퇴직"),
    LEAVE("휴직"),
    ETC("기타");

    private final String description;

    EmpStatus(String description) {
        this.description = description;
    }

}
