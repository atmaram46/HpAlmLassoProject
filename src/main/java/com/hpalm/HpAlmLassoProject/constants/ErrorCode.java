package com.hpalm.HpAlmLassoProject.constants;

public enum ErrorCode {

    EXTERNAL_API_ERROR("HP001"),
    UNAUTHORIZED_ERROR("HP002"),
    INTERNAL_SERVER_ERROR("HP003"),
    PARSING_ERROR("HP004"),
    VALIDATION_ERROR("HP005");

    public final String value;

    ErrorCode(String value) {
        this.value = value;
    }

}
