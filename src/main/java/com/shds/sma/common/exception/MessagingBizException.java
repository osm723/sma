package com.shds.sma.common.exception;

import jakarta.mail.MessagingException;

public class MessagingBizException extends RuntimeException {
    public MessagingBizException() {
        super();
    }

    public MessagingBizException(String message) {
        super(message);
    }

    public MessagingBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessagingBizException(Throwable cause) {
        super(cause);
    }

    protected MessagingBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
