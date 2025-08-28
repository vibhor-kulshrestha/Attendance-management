package com.learn.controller;

import com.learn.attendance.dto.AttendanceCheckInRequest;
import com.learn.attendance.dto.AttendanceResponse;
import com.learn.attendance.mapper.AttendanceMapper;
import com.learn.entity.Attendance;
import com.learn.service.AttendanceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.learn.attendance.mapper.AttendanceMapper.toResponse;

@RestController
@RequestMapping("/attendance")
@Validated
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/check-in")
    public AttendanceResponse checkIn(@RequestBody @Valid AttendanceCheckInRequest request) {
        Attendance saved = attendanceService.checkIn(request.employeeId());
        return toResponse(saved);
    }

    @PostMapping("/{id}/check-out")
    public AttendanceResponse checkOut(@PathVariable() @Min(1) Long id) {
        Attendance saved = attendanceService.checkOut(id);
        return toResponse(saved);
    }

    @GetMapping("/{id}")
    public AttendanceResponse getById(@PathVariable() @Min(1) Long id) {
        return toResponse(attendanceService.getById(id));
    }

    @GetMapping("/get-attendees")
    public List<AttendanceResponse> getAll() {
        return attendanceService.getAll().stream()
                .map(AttendanceMapper::toResponse)
                .toList();
    }
}
