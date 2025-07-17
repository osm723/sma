package com.shds.sma.common.constants;

public class Constants {

    private Constants() {
    }

    public static final String VALIDITY_Y = "Y";
    public static final String VALIDITY_N = "N";
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int ONE_HUNDRED_PAGE_SIZE = 100;

    public static final String NOTICE_DELETE_SUCCESS = "공지를 삭제 완료했습니다.";
    public static final String NOTICE_USE_SUCCESS = "공지를 사용 완료했습니다.";

    public static final String SYSTEM_DELETE_SUCCESS = "시스템 삭제 완료했습니다.";
    public static final String SYSTEM_USE_SUCCESS = "시스템 사용 완료했습니다.";

    public static final String CLIENT_UNUSE_SUCCESS = "그룹사를 미사용처리 완료했습니다.";
    public static final String CLIENT_USE_SUCCESS = "그룹사를 사용처리 완료했습니다.";

    public static final String MEMBER_UPDATE_SUCCESS = "직원 재직정보를 변경 완료했습니다.";

    public static final String IP_UNUSE_SUCCESS = "IP를 미사용처리 완료했습니다.";
    public static final String IP_USE_SUCCESS = "IP를 사용처리 완료했습니다.";

    public static final String CERT_UNUSE_SUCCESS = "인증서를 미사용처리 완료했습니다.";
    public static final String CERT_USE_SUCCESS = "인증서를 사용처리 완료했습니다.";

    public static class UrlPath {
        public static final String ADMIN_URL = "/admin";
        public static final String ADMIN_CLIENT_URL = "/admin/client";
        public static final String ADMIN_MEMBER_URL = "/admin/member";
        public static final String ADMIN_NOTICE_URL = "/admin/notice";
    }







}
