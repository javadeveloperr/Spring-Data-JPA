package com.example.controller;

import com.example.dto.StudentDTO;
import com.example.service.StudentService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
}
