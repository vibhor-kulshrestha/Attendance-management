package com.learn.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emp_code", unique = true)
    private String empCode;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(unique = true, length = 100)
    private String email;

    private String designation;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;
}
