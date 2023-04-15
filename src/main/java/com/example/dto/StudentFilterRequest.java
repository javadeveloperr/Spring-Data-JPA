package com.example.dto;

import com.example.enums.StudentGender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class StudentFilterRequest {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private Integer level;
    private StudentGender gender;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
