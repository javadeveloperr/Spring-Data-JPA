package com.example.entity;

import com.example.enums.StudentGender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "student_t")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    private Integer level;
    private Integer age;
    private StudentGender gender;
    @Column(name = "created_date")
    private LocalDateTime createdDate=LocalDateTime.now();
}
