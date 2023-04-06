package com.example.repository;

import com.example.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {
    CourseEntity getById(Integer id);
    List<CourseEntity> findAll();
    List<CourseEntity> getByName(String name);
    List<CourseEntity> getByPrice(Double price);
    List<CourseEntity> getByDuration(Integer duration);
    List<CourseEntity> getByPriceBetween(Double priceFrom, Double toPrice);
    List<CourseEntity> getByDurationBetween(Integer fromDuration, Integer toDuration);
    List<CourseEntity> getByCreatedDateBetween(LocalDateTime fromDate, LocalDateTime toDate);
}
