package com.learn.attendance.apierror;

import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.util.List;

public record ApiError(
        String status,
        String code,
        String message,
        List<ApiFieldError> errors,
        OffsetDateTime timestamp
) {
    public static ApiError of(HttpStatus http, String code, String message, List<ApiFieldError> errors) {
        return new ApiError(
                "FAILURE",
                code,
                message,
                errors,
                OffsetDateTime.now()
        );
    }
}
