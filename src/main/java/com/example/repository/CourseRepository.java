package com.example.repository;

import com.example.entity.CourseEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {
    @Query("from CourseEntity where id=:cId")
    CourseEntity getById(@Param("cId") Integer id);

    @Query("FROM CourseEntity ")
    List<CourseEntity> findAll();

    @Query("FROM CourseEntity where name=:cName")
    List<CourseEntity> getByName(@Param("cName") String name);

    @Query("FROM CourseEntity where price=:pr")
    List<CourseEntity> getByPrice(@Param("pr") Double price);

    List<CourseEntity> getByDuration(Integer duration);

    List<CourseEntity> getByPriceBetween(Double priceFrom, Double toPrice);

    List<CourseEntity> getByDurationBetween(Integer fromDuration, Integer toDuration);

    List<CourseEntity> getByCreatedDateBetween(LocalDateTime fromDate, LocalDateTime toDate);
}
