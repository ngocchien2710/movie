package com.vti.movie.exception;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@JsonIgnoreProperties({"strackTrace", "cause", "suppressed", "localizeMessage"})
public class AppException extends RuntimeException{
    private Instant timestamp;

    private String message;

    private int code;

    private String path;

    public AppException(ErrorResponseBase errorResponseBase) {
        this.code = errorResponseBase.getStatus().value();
        this.timestamp = Instant.now();
        this.message = errorResponseBase.getMessage();
    }

    public AppException(Exception ex) {
        this.code =500;
        this.message = ex.getMessage();
        this.timestamp = Instant.now();
    }

    public AppException(String message, int code, String path) {
        this.message = message;
        this.code = code;
        this.path = path;
        this.timestamp = Instant.now();
    }
}
