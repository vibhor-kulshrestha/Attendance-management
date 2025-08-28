package com.learn.attendance.dto.employee;

import com.learn.attendance.validation.ValidationGroups;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmployeeUpdateRequest(
        @NotNull(message = "id is required for update", groups = ValidationGroups.Update.class)
        Long id,

        @Size(max = 200, groups = ValidationGroups.Update.class)
        String name,

        @Size(max = 100, groups = ValidationGroups.Update.class)
        String designation
) {}
