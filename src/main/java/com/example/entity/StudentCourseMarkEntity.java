package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "student_course")
public class StudentCourseMarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id_id")
    private StudentEntity studentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id_id")
    private CourseEntity courseId;
    private Integer mark;
    private LocalDateTime createdDate=LocalDateTime.now();
}
