package com.learn.attendance.mapper;

import com.learn.attendance.dto.employee.EmployeeResponse;
import com.learn.entity.Employee;

public class EmployeeMapper {
    private EmployeeMapper(){}
    public static EmployeeResponse toResponse(Employee e) {
        return new EmployeeResponse(
                e.getId(),
                e.getEmpCode(),
                e.getName(),
                e.getEmail(),
                e.getDesignation(),
                e.getCreatedAt()
        );
    }
}
