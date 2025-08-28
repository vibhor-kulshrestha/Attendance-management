package com.learn.attendance.dto;

import java.time.LocalDateTime;

public record AttendanceResponse(
        Long id,
        String employeeName,
        LocalDateTime checkInTime,
        LocalDateTime checkOutTime
) {}
