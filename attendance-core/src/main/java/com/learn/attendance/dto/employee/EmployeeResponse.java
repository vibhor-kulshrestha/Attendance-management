package com.learn.attendance.dto.employee;

import java.time.OffsetDateTime;

public record EmployeeResponse(
        Long id,
        String empCode,
        String name,
        String email,
        String designation,
        OffsetDateTime createdAt
) {
}
