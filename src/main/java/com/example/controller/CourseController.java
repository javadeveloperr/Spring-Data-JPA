package com.example.controller;

import com.example.dto.CourseDTO;
import com.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping(value = "/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/list")
    public ResponseEntity<List<CourseDTO>> getAll() {
        List<CourseDTO> list = courseService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        CourseDTO dto = courseService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody CourseDTO courseDTO) {
        CourseDTO response = courseService.create(courseDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.update(id, courseDTO));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(courseService.delete(id));
    }
    @GetMapping(value = "/get-by-name/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name){
        return ResponseEntity.ok(courseService.getByName(name));
    }
    @GetMapping(value = "/get-by-price/{price}")
    public ResponseEntity<?> getByPrice(@PathVariable("price") Double price){
        return ResponseEntity.ok(courseService.getByPrice(price));
    }
    @GetMapping(value = "/get-by-duration/{duration}")
    public ResponseEntity<?> getByDuration(@PathVariable("duration") Integer duration){
        return ResponseEntity.ok(courseService.getByDuration(duration));
    }
    @GetMapping(value = "/get-by-price-between")
    public ResponseEntity<?> getByPriceBetween(@RequestParam("fromPrice") Double fromPrice,@RequestParam("toPrice") Double toPrice){
        return ResponseEntity.ok(courseService.getByPriceBetween(fromPrice, toPrice));
    }
    @GetMapping(value = "/get-by-duration-between")
    // get-by-price?fromDuration=1&toDuration=10
    public ResponseEntity<?> getByDurationBetween(@RequestParam("fromDuration")  Integer fromDuration,@RequestParam("toDuration") Integer toDuration){
        return ResponseEntity.ok(courseService.getByDurationBetween(fromDuration,toDuration));
    }
    @GetMapping(value = "/get-by-date-between")
    public ResponseEntity<?> getByCreatedDateBetween(@RequestParam("fromDate") LocalDateTime fromDate,@RequestParam("toDate") LocalDateTime toDate){
        return ResponseEntity.ok(courseService.getByCreatedDateBetween(fromDate, toDate));
    }
}
