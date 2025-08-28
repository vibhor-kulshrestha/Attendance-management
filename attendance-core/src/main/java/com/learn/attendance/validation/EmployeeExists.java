package com.learn.attendance.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmployeeExistsValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmployeeExists {
    String message() default "employee does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
