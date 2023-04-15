package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourse2 {
        private Integer id;
        private Course2DTO course;
        private Student2DTO student;
        private Integer mark;
        private LocalDateTime createdDate;

}
