package com.web.commitment.exception;

public class BaseException extends RuntimeException {

    private final ErrorCode errorCode;

    public BaseException(final String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}

