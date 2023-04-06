package com.example.service;

import com.example.dto.StudentDTO;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentEntity get(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Student not found: " + id);
        }
        return optional.get();
    }

    public StudentDTO getById(Integer id) {
        StudentEntity entity = get(id);
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        //...
        return dto;
    }

    public StudentDTO create(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new AppBadRequestException("Name qani?");
        }
        if (dto.getSurname() == null || dto.getSurname().isBlank()) {
            throw new AppBadRequestException("Surname qani?");
        }
        studentRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<StudentDTO> getAll() {
        Iterable<StudentEntity> iterable = studentRepository.findAll();
        List<StudentDTO> dtoList = new LinkedList<>();

        iterable.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            //...
            dtoList.add(dto);
        });
        return dtoList;
    }

    public boolean update(Integer id, StudentDTO studentDTO) {
        StudentEntity entity = get(id);
        entity.setName(studentDTO.getName());
        entity.setSurname(studentDTO.getSurname());
        studentRepository.save(entity);
        return true;
    }

    public boolean delete(Integer id) {
        StudentEntity entity = get(id);
        studentRepository.delete(entity);
        return true;
    }

    public List<StudentDTO> getByName(String name) {
        Iterable<StudentEntity> iterable = studentRepository.findByName(name);
        List<StudentDTO> listByname = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            //...
            listByname.add(dto);
        });
        return listByname;
    }

    public List<StudentDTO> getBySurname(String surname) {
        Iterable<StudentEntity> iterable = studentRepository.findBySurname(surname);
        List<StudentDTO> listBySurname = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            //...
            listBySurname.add(dto);
        });
        return listBySurname;
    }

    public List<StudentDTO> getByGivenDate(LocalDateTime date) {
        Iterable<StudentEntity> iterable = studentRepository.findAllByCreatedDate(date);
        List<StudentDTO> listByDate = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            //...
            listByDate.add(dto);
        });
        return listByDate;
    }

    public List<StudentDTO> getBetweenGivenDates(LocalDateTime fromDate, LocalDateTime toDate) {
        Iterable<StudentEntity> iterable = studentRepository.findAllByCreatedDateBetween(fromDate, toDate);
        List<StudentDTO> listByDate = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            //...
            listByDate.add(dto);
        });
        return listByDate;
    }

}
