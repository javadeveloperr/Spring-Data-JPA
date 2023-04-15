package com.example.controller;

import com.example.dto.StudentCourseMark2DTO;
import com.example.dto.StudentCourseMarkDTO;
import com.example.service.StudentCourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/student-course")
public class StudentCourseMarkController {
    @Autowired
    private StudentCourseMarkService studentCourseMarkService;
    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentCourseMarkDTO studentCourseMarkDTO) {
        StudentCourseMarkDTO response = studentCourseMarkService.create(studentCourseMarkDTO);
        return ResponseEntity.ok(response);
    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody StudentCourseMarkDTO studentCourseMarkDTO) {
        return ResponseEntity.ok(studentCourseMarkService.update(id, studentCourseMarkDTO));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.delete(id));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.getById2(id));
    }

    @GetMapping("/getByStudentId/{id}")
    public ResponseEntity<?> getByStudentId(@PathVariable("id") Integer id) {
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getByStudentId(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getByCourseId/{id}")
    public ResponseEntity<?> getByCourseId(@PathVariable("id") Integer id) {
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getByCourseId(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getByMark/{id}")
    public ResponseEntity<List<StudentCourseMarkDTO>> getByMark(@PathVariable("id") Integer mark) {
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getByMark(mark);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/dateFrom")
    public ResponseEntity<List<StudentCourseMarkDTO>> getByFromDate(@RequestParam("from") LocalDateTime fromDate) {
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getTimeFrom(fromDate);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getByStudentCourseId/{id}")
    public ResponseEntity<?> getByStudentCourseId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.getByIdStudentFull(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentCourseMarkDTO>> getAll() {
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getByStudentMark/{id}")
    public ResponseEntity<List<StudentCourseMarkDTO>> getByStudentMarkFromDate(@PathVariable("id") Integer id,
                                                                           @RequestParam("from") LocalDate fromDate) {
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getStudentMarkTimeFrom(id, fromDate);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/date/{id}")
    public ResponseEntity<List<StudentCourseMarkDTO>> getByListFromAndTo(@PathVariable("id") Integer id,
                                                                     @RequestParam("dateFrom") LocalDate fromDate,
                                                                     @RequestParam("dateTo") LocalDate dateTo) {
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getStudentIdTimeFromAndTo(id, fromDate, dateTo);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/sortCreatedDate")
    public ResponseEntity<?> getBySortCreatedDate(@RequestParam("studentId") Integer id, @RequestParam("courseId") Integer courseId) {
        return ResponseEntity.ok(studentCourseMarkService.getCourseMark(id, courseId));
    }

    @GetMapping("/finalMarkAndCourse/{id}")
    public ResponseEntity<?> getByThreeMark(@PathVariable("id") Integer id) {
        List<StudentCourseMark2DTO> list = studentCourseMarkService.getStudentIdThreeMark(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/finalBigMarkAndCourse/{id}")
    public ResponseEntity<List<StudentCourseMarkDTO>> getByBigThreeMark(@PathVariable("id") Integer id) {
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getStudentIdBigThreeMark(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/firstMarkAndCourse/{id}")
    public ResponseEntity<List<StudentCourseMarkDTO>> getByFirstThreeMark(@PathVariable("id") Integer id) {
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getStudentIdFirstThreeMark(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getCourseIdFirstMArk")
    public ResponseEntity<?> getCourseIdFirstMArk(@RequestParam("courseId") Integer id, @RequestParam("studentId") Integer courseId) {
        return ResponseEntity.ok(studentCourseMarkService.getCourseIdFirstMark(courseId, id));
    }

    @GetMapping("/getCourseIdBigFirstMArk")
    public ResponseEntity<?> getCourseIdBigFirstMArk(@RequestParam("courseId") Integer id, @RequestParam("studentId") Integer courseId) {
        return ResponseEntity.ok(studentCourseMarkService.getCourseIdBigFirstMark(courseId, id));
    }

    @GetMapping("/getAvgMarkAndCourse/{id}")
    public ResponseEntity<?> getAvgMarkAndCourse(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.getStudentIdAvgMarkAndCourse(id));
    }

    @GetMapping("/getAvgMarkAndCourseId")
    public ResponseEntity<?> getAvgMarkAndCourseId(@RequestParam("id") Integer id, @RequestParam("cid") Integer cid) {
        return ResponseEntity.ok(studentCourseMarkService.getStudentIdAvgMarkAndCourseIdgjasg(id, cid));
    }

    @GetMapping("/getMaxMarkAnStudentIdMark")
    public ResponseEntity<List<Integer>> getMaxMarkAnStudentIdMark(@RequestParam("id") Integer id, @RequestParam("mark") Integer mark) {
        return ResponseEntity.ok(studentCourseMarkService.getStudentIdMaxMarkAndStudentMark(id, mark));
    }

    @GetMapping("/getMaxMarkAndCourseId/{id}")
    public ResponseEntity<?> getMaxMarkAndCourseId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.getStudentIdMaxMarkAndCourseId(id));
    }

    @GetMapping("/getAvgMarkCourseId/{id}")
    public ResponseEntity<?> getAvgMarkCourseId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.getStudentIdAvgMarkAndCourseIdgjasg(id));
    }
    @GetMapping("/getAvgMarkCourseIdCount/{id}")
    public ResponseEntity<?> getAvgMarkCourseIdCount(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.getStudentIdAvgMarkAndCourseIdCount(id));
    }
}
