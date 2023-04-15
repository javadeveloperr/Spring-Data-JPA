package com.example.repository;

import com.example.dto.StudentFilterRequest;
import com.example.entity.StudentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentCustomRepository {
    @Autowired
    private EntityManager entityManager;

    public List<StudentEntity> getAll() {
        Query query = this.entityManager.createQuery("select s from StudentEntity as s");
        List studentList = query.getResultList();
        return studentList;
    }

    //    public List<StudentEntity> filter(StudentFilterRequest filterDTO) {
//        StringBuilder builder = new StringBuilder();
//        builder.append("Select s From StudentEntity as s where visible = true");
//        if (filterDTO.getName() != null) {
//            builder.append(" and s.name = :name");
//        }
//        if (filterDTO.getSurname() != null) {
//            builder.append(" and s.surname = :surname");
//        }
//        // ....
//
//        Query query = this.entityManager.createQuery(builder.toString());
//        if (filterDTO.getName() != null) {
//            query.setParameter("name", filterDTO.getName());
//        }
//        if (filterDTO.getSurname() != null) {
//            query.setParameter("surname", filterDTO.getSurname());
//        }
//
//        List studentList = query.getResultList();
//
//        return studentList;
//    }
    public List<StudentEntity> filter(StudentFilterRequest filterDTO) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        builder.append("Select s From StudentEntity as s where visible = true");

        if (filterDTO.getName() != null) {
            builder.append(" and s.name = :name");
            params.put("name", filterDTO.getName());
        }
        if (filterDTO.getSurname() != null) {
            builder.append(" and s.surname = :surname");
            params.put("surname", filterDTO.getSurname());
        }
        if (filterDTO.getAge() != null) {
            builder.append(" and s.age = :age");
            params.put("age", filterDTO.getAge());
        }
        if (filterDTO.getLevel() != null) {
            builder.append(" and s.level = :level");
            params.put("level", filterDTO.getLevel());
        }
        if (filterDTO.getId() != null) {
            builder.append(" and s.id =: id");
            params.put("id", filterDTO.getId());
        }
        if (filterDTO.getDateFrom() != null && filterDTO.getDateTo() != null) {
            builder.append(" and s.createdDate between :dateFrom and :dateTo ");
            params.put("dateFrom", LocalDateTime.of(filterDTO.getDateFrom(), LocalTime.MIN));
            params.put("dateTo", LocalDateTime.of(filterDTO.getDateTo(), LocalTime.MAX));
        }
        else if (filterDTO.getDateFrom() != null) {
            builder.append(" and s.createdDate >= :dateFrom ");
            params.put("dateFrom", LocalDateTime.of(filterDTO.getDateFrom(), LocalTime.MIN));
        }
        else if (filterDTO.getDateTo() != null) {
            builder.append(" and s.createdDate <= :dateTo ");
            params.put("dateTo", LocalDateTime.of(filterDTO.getDateTo(), LocalTime.MIN));
        }
        // ....
        Query query = this.entityManager.createQuery(builder.toString());
        for (Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        List studentList = query.getResultList();
        return studentList;
    }
}
