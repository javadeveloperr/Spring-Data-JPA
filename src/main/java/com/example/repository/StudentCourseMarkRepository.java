package com.example.repository;

import com.example.mapper.CourseInfoMapper;
import com.example.entity.StudentCourseMarkEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface StudentCourseMarkRepository extends CrudRepository<StudentCourseMarkEntity, Integer> {
    List<StudentCourseMarkEntity> getAllByMark(Integer mark);

    List<StudentCourseMarkEntity> getByCreatedDateIsAfter(LocalDateTime fromDate);

    List<StudentCourseMarkEntity> getAllByStudentId(Integer id);

    List<StudentCourseMarkEntity> getAllByCourseId(Integer id);
    List<StudentCourseMarkEntity> getAllByStudentIdAndCreatedDate(Integer id, LocalDate fromDate);

    List<StudentCourseMarkEntity> getAllByStudentIdAndCreatedDateBetween(Integer integer, LocalDate fromDate,LocalDate dateTo);
    List<StudentCourseMarkEntity> getAllByStudentIdAndCourseIdOrderByCreatedDateDesc(Integer id, Integer courseId);
    List<StudentCourseMarkEntity> findTop1ByStudentIdOrderByMarkAsc(Integer student_id);

    List<StudentCourseMarkEntity> findTop3ByStudentIdOrderByMarkDesc(Integer student_id);
    List<StudentCourseMarkEntity> findTop1ByStudentIdOrderByCreatedDate(Integer student_id);
    List<StudentCourseMarkEntity> findTop1ByStudentIdAndAndCourseIdOrderByCreatedDate(Integer courseId, Integer student_id);
    List<StudentCourseMarkEntity> findTop1ByStudentIdAndAndCourseIdOrderByMarkDesc(Integer courseId, Integer student_id);
    @Query(value = "select avg(s.mark) from student_course as s where student_id=:sid ",nativeQuery = true)
    Double orderAvgByMark(@Param("sid") Integer id);
    @Query(value = "select avg(s.mark) from student_course as s where student_id = :sid and course_id_id=:cid ",nativeQuery = true)
    Double avgByMarkStudentAndCourseId(@Param("sid") Integer id, @Param("cid") Integer cid );

    @Query(value = "select s.mark from student_course as s where course_id=:sid and mark > :mark",nativeQuery = true)
    List<Integer> orderMaxByMarkStudentId(@Param("sid") Integer id,@Param("mark") Integer mark);

    @Query(value = "select max(s.mark) from student_course as s where course_id_id=:sid ",nativeQuery = true)
    Integer orderMaxByMarkCourseId(@Param("sid") Integer id);
    @Query(value = "select avg(s.mark) from student_course as s where course_id_id=:sid ",nativeQuery = true)
    Integer avgByMarkStudentAndCourseId(@Param("sid") Integer id);
    @Query(value = "select count(s.mark) from student_course as s where course_id_id=:sid ",nativeQuery = true)
    Integer countByMarkStudentAndCourseId(@Param("sid") Integer id);
    @Query(value = "SELECT course_id from  student_course_mark where student_id = :studentId order by created_date desc limit 1 ", nativeQuery = true)
    Integer findLastCourseMarker(@Param("studentId") Integer studentId);

//    @Query(value = "SELECT c.id, c.name " +
//            " from  student_course_mark as scm " +
//            " inner join course_t as c on c.id = scm.course_id " +
//            " where scm.student_id = :studentId  " +
//            "order by scm.created_date desc limit 1 ", nativeQuery = true)
//    List<Object[]> findLastCourseMarkerAsNative(@Param("studentId") Integer studentId);
@Query(value = "SELECT scm.student_id as sId, scm.mark as mark, " +
        "  c.id as cId, c.name as cName " +
        " from  student_course_mark as scm " +
        " inner join course_t as c on c.id = scm.course_id " +
        " where scm.student_id = :studentId  " +
        "order by scm.created_date desc limit 1 ", nativeQuery = true)
CourseInfoMapper findLastCourseMarkerAsNativeMapping(@Param("studentId") Integer studentId);
}
