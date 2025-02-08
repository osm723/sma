package com.shds.sma.cert.types;

import lombok.Getter;

@Getter
public enum CertType {
    AUTH("인증"),
    SELF("자체인증서"),
    EXTERNAL("외부인증서"),
    INNER("내부인증서"),
    GROUPWARE("그룹웨어인증서");

    private final String description;

    CertType(String description) {
        this.description = description;
    }
}
