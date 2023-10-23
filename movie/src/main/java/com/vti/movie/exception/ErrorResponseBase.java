package com.vti.movie.exception;

import org.springframework.http.HttpStatus;

public enum ErrorResponseBase {
    NOT_FOUND(HttpStatus.NOT_FOUND, "Đối tượng k tồn tại"),
    USERNAME_EXISTED(HttpStatus.INTERNAL_SERVER_ERROR, "Đối tượng đã tồn tại"),
    MIN_MAX_INVALID(HttpStatus.BAD_REQUEST, "Số min phải nhỏ hơn max"),
    lOGIN_FAILS(HttpStatus.UNAUTHORIZED, "Người dùng k tồn tại"),
    lOGIN_FAILS_USERNAME(HttpStatus.UNAUTHORIZED, "Người dùng k tồn tại"),
    lOGIN_FAILS_PASSWORD(HttpStatus.UNAUTHORIZED, "Người dùng k tồn tại");



    public final HttpStatus status;
    public final String message;

    ErrorResponseBase(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
