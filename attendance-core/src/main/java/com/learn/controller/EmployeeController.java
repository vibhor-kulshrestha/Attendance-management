package com.learn.controller;

import com.learn.attendance.dto.employee.EmployeeCreateRequest;
import com.learn.attendance.dto.employee.EmployeeResponse;
import com.learn.attendance.dto.employee.EmployeeUpdateRequest;
import com.learn.attendance.validation.ValidationGroups;
import com.learn.entity.Employee;
import com.learn.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.learn.attendance.mapper.EmployeeMapper.toResponse;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
    @PostMapping("/create")
    public ResponseEntity<EmployeeResponse> create(@RequestBody @Validated(ValidationGroups.Create.class) EmployeeCreateRequest req) {
        Employee saved = service.create(req.name(), req.email(), req.designation());
        return ResponseEntity.status(201).body(toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(@PathVariable Long id,
                                                   @RequestBody @Validated(ValidationGroups.Update.class) EmployeeUpdateRequest req) {
        Long reqId = req.id() == null ? id : req.id();
        Employee updated = service.update(reqId, req.name(), req.designation());
        return ResponseEntity.ok(toResponse(updated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(toResponse(service.getById(id)));
    }
}
