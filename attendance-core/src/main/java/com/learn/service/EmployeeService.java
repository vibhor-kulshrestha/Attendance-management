package com.learn.service;

import com.learn.entity.Employee;
import com.learn.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public Employee create(String name, String email, String designation) {
        if (email != null && repo.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }
        Employee e = new Employee().builder()
                .empCode(generateEmpCode())
                .name(name)
                .email(email)
                .designation(designation)
                .createdAt(OffsetDateTime.now())
                .build();
        return repo.save(e);
    }

    private String generateEmpCode() {
        // simple emp code generator; replace with business logic later
        return "EMP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    @Cacheable(cacheNames = "employeeById", key = "#id")
    public Employee getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found with id " + id));
    }

    @Transactional
    @CacheEvict(cacheNames = "employeeById", key = "#req.id")
    public Employee update(Long id, String name, String designation) {
        Employee e = getById(id);
        if (name != null) e.setName(name);
        if (designation != null) e.setDesignation(designation);
        return repo.save(e);
    }
}
