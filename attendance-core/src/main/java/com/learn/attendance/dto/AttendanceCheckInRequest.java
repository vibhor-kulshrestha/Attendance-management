package com.learn.attendance.dto;

import com.learn.attendance.validation.EmployeeExists;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AttendanceCheckInRequest(
        @NotNull(message = "employeeId is required")
        Long employeeId
) {}
