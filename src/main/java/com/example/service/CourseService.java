package com.example.service;

import com.example.dto.CourseDTO;
import com.example.entity.CourseEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    public CourseEntity get(Integer id) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Student not found: " + id);
        }
        return optional.get();
    }

    public CourseDTO getById(Integer id) {
        CourseEntity entity = get(id);
        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        //...
        return dto;
    }

    public CourseDTO create(CourseDTO dto) {
        CourseEntity entity = new CourseEntity();
        entity.setName(dto.getName());
        entity.setDuration(dto.getDuration());
        entity.setPrice(dto.getPrice());
        entity.setCreatedDate(dto.getCreatedDate());
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new AppBadRequestException("course name qani?");
        }

        courseRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<CourseDTO> getAll() {
        Iterable<CourseEntity> iterable = courseRepository.findAll();
        List<CourseDTO> dtoList = new LinkedList<>();

        iterable.forEach(entity -> {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDuration(entity.getDuration());
            dto.setPrice(entity.getPrice());
            dto.setCreatedDate(entity.getCreatedDate());
            //...
            dtoList.add(dto);
        });
        return dtoList;
    }

    public boolean update(Integer id, CourseDTO courseDTO) {
        CourseEntity entity = get(id);
        entity.setName(courseDTO.getName());
        entity.setDuration(courseDTO.getDuration());
        entity.setPrice(courseDTO.getPrice());
        entity.setCreatedDate(courseDTO.getCreatedDate());
        return true;
    }

    public boolean delete(Integer id) {
        CourseEntity entity = get(id);
        courseRepository.delete(entity);
        return true;
    }

    public List<CourseDTO> getByName(String name) {
        Iterable<CourseEntity> iterable = courseRepository.getByName(name);
        List<CourseDTO> listByname = new LinkedList<>();
        iterable.forEach(entity -> {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDuration(dto.getDuration());
            dto.setPrice(dto.getPrice());
            dto.setCreatedDate(dto.getCreatedDate());
            //...
            listByname.add(dto);
        });
        return listByname;
    }
}
