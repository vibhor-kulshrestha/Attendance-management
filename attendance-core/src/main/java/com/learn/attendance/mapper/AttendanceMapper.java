package com.learn.attendance.mapper;

import com.learn.attendance.dto.AttendanceResponse;
import com.learn.entity.Attendance;

public class AttendanceMapper {
    private AttendanceMapper() {
    }
    public static AttendanceResponse toResponse(Attendance entity) {
        return new AttendanceResponse(
                entity.getId(),
                entity.getEmployeeName(),
                entity.getCheckInTime(),
                entity.getCheckOutTime()
        );
    }
}
