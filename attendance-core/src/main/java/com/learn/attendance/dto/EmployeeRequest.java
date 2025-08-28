package com.learn.attendance.dto;

import com.learn.attendance.validation.ValidationGroups;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmployeeRequest(
        @NotNull(message = "id is required for update", groups = ValidationGroups.Update.class)
        Long id,

        @NotBlank(message = "name is required", groups = ValidationGroups.Create.class)
        @Size(max = 100, groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
        String name,

        @Email(message = "must be a valid email", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
        String email
) {
}
