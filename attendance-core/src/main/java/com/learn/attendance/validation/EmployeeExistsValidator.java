package com.learn.attendance.validation;


import com.learn.repository.EmployeeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeExistsValidator implements ConstraintValidator<EmployeeExists, Long> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return employeeRepository.existsById(value);
    }
}
