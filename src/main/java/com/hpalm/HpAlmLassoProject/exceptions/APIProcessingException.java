package com.hpalm.HpAlmLassoProject.exceptions;

public class APIProcessingException extends  Exception {
    public APIProcessingException(String message) {
        super(message);
    }

    public APIProcessingException(String message, Throwable th) {
        super(message, th);
    }
}
