package com.example.service;

import com.example.mapper.CourseInfoMapper;
import com.example.dto.CourseDTO;
import com.example.dto.StudentCourseMark2DTO;
import com.example.dto.StudentCourseMarkDTO;
import com.example.entity.StudentCourseMarkEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.CourseRepository;
import com.example.repository.StudentCourseMarkRepository;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseMarkService {
    @Autowired
    private StudentCourseMarkRepository studentCourseMarkRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    public StudentCourseMarkEntity get(Integer id) {
        Optional<StudentCourseMarkEntity> optional = studentCourseMarkRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Student not found: " + id);
        }
        return optional.get();
    }

    public StudentCourseMarkDTO getById(Integer id) {
        StudentCourseMarkEntity entity = get(id);
        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
        dto.setId(entity.getId());
        dto.setMark(entity.getMark());
        dto.setCourseId(entity.getCourseId().getId());
        dto.setStudentId(entity.getStudentId().getId());
        dto.setCreatedDate(entity.getCreatedDate());
        //...
        return dto;
    }

    public StudentCourseMarkDTO create(StudentCourseMarkDTO studentCourseMarkDTO) {
        StudentCourseMarkEntity entity = new StudentCourseMarkEntity();
        entity.setId(studentCourseMarkDTO.getId());
        entity.setMark(studentCourseMarkDTO.getMark());
        entity.setCourseId(courseRepository.getById(studentCourseMarkDTO.getCourseId()));
        entity.setStudentId(studentRepository.getById(studentCourseMarkDTO.getStudentId()));
        entity.setCreatedDate(studentCourseMarkDTO.getCreatedDate());
        if (entity.getCourseId() == null || entity.getStudentId() == null) {
            throw new AppBadRequestException("Course id or Student id required?");
        }
        studentCourseMarkRepository.save(entity);
        return studentCourseMarkDTO;
    }

    public boolean update(Integer id, StudentCourseMarkDTO studentCourseMarkDTO) {
        StudentCourseMarkEntity entity=get(id);
        entity.setCourseId(courseRepository.getById(studentCourseMarkDTO.getCourseId()));
        entity.setStudentId(studentRepository.getById(studentCourseMarkDTO.getStudentId()));
        entity.setMark(studentCourseMarkDTO.getMark());
        studentCourseMarkRepository.save(entity);
        return true;
    }

    public boolean delete(Integer id) {
        StudentCourseMarkEntity entity =get(id);
        studentCourseMarkRepository.delete(entity);
        return true;
    }
    public StudentCourseMarkDTO getById2(Integer id) {
        StudentCourseMarkEntity entity = get(id);
        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
        dto.setId(entity.getId());
        dto.setStudentId(entity.getStudentId().getId());
        dto.setCourseId(entity.getCourseId().getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public StudentCourseMarkDTO getByIdStudentFull(Integer id) {
        StudentCourseMarkEntity entity = get(id);
        StudentCourseMarkDTO response = (StudentCourseMarkDTO) studentCourseMarkRepository.getAllByStudentId(id);
        return response;
    }

    public List<StudentCourseMarkDTO> getByStudentId(Integer id) {
        List<StudentCourseMarkDTO> list = new LinkedList<>();
        List<StudentCourseMarkEntity> entity = studentCourseMarkRepository.getAllByStudentId(id);
        entity.forEach(studentCourseMarkEntity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(studentCourseMarkEntity.getId());
            dto.setStudentId(studentCourseMarkEntity.getStudentId().getId());
            dto.setCourseId(studentCourseMarkEntity.getCourseId().getId());
            dto.setCreatedDate(studentCourseMarkEntity.getCreatedDate());
            list.add(dto);
        });
        return list;

    }

    public List<StudentCourseMarkDTO> getByCourseId(Integer id) {
        List<StudentCourseMarkEntity> entity = studentCourseMarkRepository.getAllByCourseId(id);
        List<StudentCourseMarkDTO> list = new LinkedList<>();
        entity.forEach(studentCourseMarkEntity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(studentCourseMarkEntity.getId());
            dto.setStudentId(studentCourseMarkEntity.getStudentId().getId());
            dto.setCourseId(studentCourseMarkEntity.getCourseId().getId());
            dto.setCreatedDate(studentCourseMarkEntity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }

    public List<StudentCourseMarkDTO> getByMark(Integer mark) {
        List<StudentCourseMarkEntity> entityList = studentCourseMarkRepository.getAllByMark(mark);
        List<StudentCourseMarkDTO> list = new LinkedList<>();
        for (StudentCourseMarkEntity entity : entityList) {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setCourseId(entity.getCourseId().getId());
            dto.setStudentId(entity.getStudentId().getId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        }
        return list;
    }

    public List<StudentCourseMarkDTO> getTimeFrom(LocalDateTime fromDate) {
        List<StudentCourseMarkEntity> entityList = studentCourseMarkRepository.getByCreatedDateIsAfter(fromDate);
        List<StudentCourseMarkDTO> list = new LinkedList<>();
        for (StudentCourseMarkEntity entity : entityList) {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setCourseId(entity.getCourseId().getId());
            dto.setStudentId(entity.getStudentId().getId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        }
        return list;
    }

    public List<StudentCourseMarkDTO> getAll() {
        Iterable<StudentCourseMarkEntity> entityIterable = studentCourseMarkRepository.findAll();
        List<StudentCourseMarkDTO> list = new LinkedList<>();
        entityIterable.forEach(studentEntity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(studentEntity.getId());
            dto.setStudentId(studentEntity.getStudentId().getId());
            dto.setCourseId(studentEntity.getCourseId().getId());
            dto.setMark(studentEntity.getMark());
            dto.setCreatedDate(studentEntity.getCreatedDate());
            dto.setId(studentEntity.getId());
            list.add(dto);
        });
        return list;
    }

    public List<StudentCourseMarkDTO> getStudentMarkTimeFrom(Integer id, LocalDate fromDate) {
        List<StudentCourseMarkEntity> entity = studentCourseMarkRepository.getAllByStudentIdAndCreatedDate(id, fromDate);
        List<StudentCourseMarkDTO> list = new LinkedList<>();
        entity.forEach(studentEntity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(studentEntity.getId());
            dto.setStudentId(studentEntity.getStudentId().getId());
            dto.setCourseId(studentEntity.getCourseId().getId());
            dto.setMark(studentEntity.getMark());
            dto.setCreatedDate(studentEntity.getCreatedDate());
            dto.setId(studentEntity.getId());
            list.add(dto);
        });
        return list;
    }

    public List<StudentCourseMarkDTO> getStudentIdTimeFromAndTo(Integer id, LocalDate fromDate, LocalDate dateTo) {
        List<StudentCourseMarkEntity> entity = studentCourseMarkRepository.getAllByStudentIdAndCreatedDateBetween(id, fromDate, dateTo);
        List<StudentCourseMarkDTO> list = new LinkedList<>();
        entity.forEach(studentEntity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(studentEntity.getId());
            dto.setStudentId(studentEntity.getStudentId().getId());
            dto.setCourseId(studentEntity.getCourseId().getId());
            dto.setMark(studentEntity.getMark());
            dto.setCreatedDate(studentEntity.getCreatedDate());
            dto.setId(studentEntity.getId());
            list.add(dto);
        });
        return list;
    }

    public List<StudentCourseMarkDTO> getCourseMark(Integer id, Integer courseId) {
        List<StudentCourseMarkEntity> entityList = studentCourseMarkRepository.getAllByStudentIdAndCourseIdOrderByCreatedDateDesc(id, courseId);
        List<StudentCourseMarkDTO> list = new LinkedList<>();
        entityList.forEach(studentCourseMarkEntity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setMark(studentCourseMarkEntity.getMark());
            dto.setStudentId(studentCourseMarkEntity.getStudentId().getId());
            dto.setCourseId(studentCourseMarkEntity.getCourseId().getId());
            dto.setCreatedDate(studentCourseMarkEntity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }

    public List<StudentCourseMark2DTO> getStudentIdThreeMark(Integer id) {
        List<StudentCourseMarkEntity> entityList = studentCourseMarkRepository.findTop1ByStudentIdOrderByMarkAsc(id);
        List<StudentCourseMark2DTO> list = new LinkedList<>();
        entityList.forEach(studentCourseMarkEntity -> {
            StudentCourseMark2DTO dto = new StudentCourseMark2DTO();
            dto.setId(studentCourseMarkEntity.getId());
//            dto.setStudent(studentRepository.getById(id));
//            dto.setCourse(courseRepository.getById(id));
            dto.setMark(studentCourseMarkEntity.getMark());
            dto.setCreatedDate(studentCourseMarkEntity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }

    public List<StudentCourseMarkDTO> getStudentIdBigThreeMark(Integer id) {
        List<StudentCourseMarkEntity> entityList = studentCourseMarkRepository.findTop3ByStudentIdOrderByMarkDesc(id);
        List<StudentCourseMarkDTO> list = new LinkedList<>();
        entityList.forEach(studentCourseMarkEntity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setMark(studentCourseMarkEntity.getMark());
            dto.setStudentId(studentCourseMarkEntity.getStudentId().getId());
            dto.setCourseId(studentCourseMarkEntity.getCourseId().getId());
            dto.setCreatedDate(studentCourseMarkEntity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }

    public List<StudentCourseMarkDTO> getStudentIdFirstThreeMark(Integer id) {
        List<StudentCourseMarkEntity> entityList = studentCourseMarkRepository.findTop1ByStudentIdOrderByCreatedDate(id);
        List<StudentCourseMarkDTO> list = new LinkedList<>();
        entityList.forEach(studentCourseMarkEntity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setMark(studentCourseMarkEntity.getMark());
            dto.setStudentId(studentCourseMarkEntity.getStudentId().getId());
            dto.setCourseId(studentCourseMarkEntity.getCourseId().getId());
            dto.setCreatedDate(studentCourseMarkEntity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }

    public List<StudentCourseMarkDTO> getCourseIdFirstMark(Integer courseId, Integer id) {
        List<StudentCourseMarkEntity> entityList = studentCourseMarkRepository.findTop1ByStudentIdAndAndCourseIdOrderByCreatedDate(courseId, id);
        List<StudentCourseMarkDTO> list = new LinkedList<>();
        entityList.forEach(studentCourseMarkEntity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(studentCourseMarkEntity.getId());
            dto.setMark(studentCourseMarkEntity.getMark());
            dto.setStudentId(studentRepository.getById(id).getId());
            dto.setCourseId(studentCourseMarkEntity.getCourseId().getId());
            dto.setCreatedDate(studentCourseMarkEntity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }

    public List<StudentCourseMarkDTO> getCourseIdBigFirstMark(Integer courseId, Integer id) {
        List<StudentCourseMarkEntity> entityList = studentCourseMarkRepository.findTop1ByStudentIdAndAndCourseIdOrderByMarkDesc(courseId, id);
        List<StudentCourseMarkDTO> list = new LinkedList<>();
        entityList.forEach(studentCourseMarkEntity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setMark(studentCourseMarkEntity.getMark());
            dto.setStudentId(studentCourseMarkEntity.getStudentId().getId());
            dto.setCourseId(studentCourseMarkEntity.getCourseId().getId());
            dto.setCreatedDate(studentCourseMarkEntity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }
    public Double getStudentIdAvgMarkAndCourse(Integer id) {
        Double avg = studentCourseMarkRepository.orderAvgByMark(id);
        return avg;
    }

    public Double getStudentIdAvgMarkAndCourseIdgjasg(Integer id, Integer cid) {
        Double avg = studentCourseMarkRepository.avgByMarkStudentAndCourseId(id,cid);
        return avg;
    }

    public List<Integer> getStudentIdMaxMarkAndStudentMark(Integer id, Integer mark) {
        List<Integer> max = studentCourseMarkRepository.orderMaxByMarkStudentId(id,mark);
        return max;
    }
    public Integer getStudentIdMaxMarkAndCourseId(Integer id) {
        Integer avg = studentCourseMarkRepository.orderMaxByMarkCourseId(id);
        return avg;
    }
    public Integer getStudentIdAvgMarkAndCourseIdgjasg(Integer id) {
        Integer avg = studentCourseMarkRepository.avgByMarkStudentAndCourseId(id);
        return avg;
    }
    public Integer getStudentIdAvgMarkAndCourseIdCount(Integer id) {
        Integer avg = studentCourseMarkRepository.countByMarkStudentAndCourseId(id);
        return avg;
    }
//    public void test() {
//        List<Object[]> courseObjList = studentCourseMarkRepository.findLastCourseMarkerAsNative(1);
//        if (courseObjList.size() > 0) {
//            Object[] courseObj = courseObjList.get(0);
//
//            CourseDTO courseDTO = new CourseDTO();
//            courseDTO.setId((Integer) courseObj[0]);
//            courseDTO.setName((String) courseObj[1]);
//            System.out.println(courseDTO);
//        }
//
//        System.out.println("dasda");
//    }
    public void test2() {
        CourseInfoMapper courseInfoMapper = studentCourseMarkRepository.findLastCourseMarkerAsNativeMapping(1);
        if (courseInfoMapper != null) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(courseInfoMapper.getCId());
            courseDTO.setName(courseInfoMapper.getCName());
            System.out.println(courseDTO +" "+ courseInfoMapper.getMark());
        }
        System.out.println("dasda");
    }


}
