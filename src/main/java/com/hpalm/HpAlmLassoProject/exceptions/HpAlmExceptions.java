package com.hpalm.HpAlmLassoProject.exceptions;

import com.hpalm.HpAlmLassoProject.constants.ErrorCode;
import lombok.Getter;

@Getter
public class HpAlmExceptions extends RuntimeException {

    private final ErrorCode errorCode;

    public HpAlmExceptions(String arg, ErrorCode errorCode) {
        super(arg);
        this.errorCode = errorCode;
    }

    public HpAlmExceptions(String arg, ErrorCode errorCode, Throwable cause) {
        super(arg, cause);
        this.errorCode = errorCode;
    }
}
