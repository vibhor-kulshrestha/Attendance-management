package com.learn.service;

import com.learn.entity.Attendance;
import com.learn.entity.Employee;
import com.learn.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceService {
    private AttendanceRepository attendanceRepository;
    private EmployeeService employeeService;

    public AttendanceService(AttendanceRepository attendanceRepository, EmployeeService employeeService) {
        this.attendanceRepository = attendanceRepository;
        this.employeeService= employeeService;
    }

    public List<Attendance> getAll() {
        return attendanceRepository.findAll();
    }

    public Attendance checkIn(Long employeeId) {
        Employee employee = employeeService.getById(employeeId);
        Attendance attendance = Attendance.builder()
                .employeeName(employee.getName())
                .checkInTime(LocalDateTime.now())
                .build();
        return attendanceRepository.save(attendance);
    }

    public Attendance checkOut(Long id) {
        Attendance existing = attendanceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Attendance not found with id " + id));
        if (existing.getCheckOutTime() != null) {
            throw new IllegalStateException("Already checked out");
        }
        existing.setCheckOutTime(LocalDateTime.now());
        return attendanceRepository.save(existing);
    }

    public Attendance getById(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Attendance not found with id " + id));
    }
}
