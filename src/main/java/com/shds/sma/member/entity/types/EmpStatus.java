package com.shds.sma.member.entity.types;

public enum EmpStatus {
    EMPLOYEE("재직"),
    RETIRE("퇴직"),
    LEAVE("휴직"),
    ETC("기타");

    private final String empStatus;

    EmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    public String getEmpStatus() {
        return empStatus;
    }
}
