package com.learn.attendance.dto.employee;

import com.learn.attendance.validation.ValidationGroups;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmployeeCreateRequest(
        @NotBlank(message = "name is required", groups = ValidationGroups.Create.class)
        @Size(max = 200, groups = ValidationGroups.Create.class)
        String name,

        @Email(message = "email must be valid", groups = ValidationGroups.Create.class)
        String email,

        @Size(max = 100, groups = ValidationGroups.Create.class)
        String designation
) {
}
