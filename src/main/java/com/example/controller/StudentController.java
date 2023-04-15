package com.example.controller;

import com.example.dto.StudentDTO;
import com.example.dto.StudentFilterRequest;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public ResponseEntity<List<StudentDTO>> getAll() {
        List<StudentDTO> list = studentService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        StudentDTO dto = studentService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentDTO studentDTO) {
        StudentDTO response = studentService.create(studentDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.update(id, studentDTO));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.delete(id));
    }
    @GetMapping(value = "/get-by-name/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name){
        return ResponseEntity.ok(studentService.getByName(name));
    }
    @GetMapping(value = "/get-by-surname/{surname}")
    public ResponseEntity<?> getBySurname(@PathVariable("surname") String surname){
        return ResponseEntity.ok(studentService.getBySurname(surname));
    }
    @GetMapping(value = "/filter")
    public ResponseEntity<?> filter(@RequestBody StudentFilterRequest studentFilterRequest){
        return ResponseEntity.ok(studentService.filter(studentFilterRequest));
    }
    @PostMapping("/paging")
    public ResponseEntity<?> paging(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "30") int size,
            @RequestBody StudentFilterRequest dto) {
        return ResponseEntity.ok(studentService.paging(dto, page, size));

    }

    @PostMapping(value = "/paging-name")
    public ResponseEntity<Page<StudentDTO>> pagingWithName(@RequestParam(value = "page", defaultValue = "1") int page,
                                                           @RequestParam(value = "size", defaultValue = "30") int size,
                                                           @RequestBody StudentFilterRequest filter) {
        Page<StudentDTO> response = studentService.paginationWithName(filter.getName(), page, size);
        return ResponseEntity.ok(response);
    }

    // Student Pagination by given Level. List should be sorted by id.
    @GetMapping("/paging-Level")
    public ResponseEntity<Page<StudentDTO>> pagingWithLevel(@RequestParam(value = "page", defaultValue = "1") int page,
                                                            @RequestParam(value = "size", defaultValue = "30") int size,
                                                            @RequestParam(value = "level") Integer level) {
        Page<StudentDTO> response = studentService.pagingWithLevel(level, page, size);
        return ResponseEntity.ok(response);
    }

    // Pagination by given gender.  List should be sorted by createdDate.
    @PostMapping("/paging-gender")
    public ResponseEntity<Page<StudentDTO>> pagingWithGender(@RequestParam(value = "page", defaultValue = "1") int page,
                                                             @RequestParam(value = "size", defaultValue = "30") int size,
                                                             @RequestBody StudentFilterRequest filter) {
        Page<StudentDTO> response = studentService.pagingWithGender(filter.getGender().toString(), page, size);
        return ResponseEntity.ok(response);
    }
}
