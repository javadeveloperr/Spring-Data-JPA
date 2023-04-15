package com.example.repository;

import com.example.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer>,
        PagingAndSortingRepository<StudentEntity, Integer> {
//    StudentEntity findByPhone(String phone);   // from StudentEntity where phone =:phone

//    Optional<StudentEntity> findByPhone(String phone); // from StudentEntity where phone =:phone

    ///StudentEntity findByName(String name); // from StudentEntity where name =:name

    List<StudentEntity> findBySurname(String surname);

    List<StudentEntity> findByName(String name);

    List<StudentEntity> findAllByName(String name);

    List<StudentEntity> findAllByLevel(String level);

    List<StudentEntity> findAllByAge(Integer age);

    StudentEntity getById(Integer studentId);
    List<StudentEntity> findAllByGender(String gender);

    List<StudentEntity> findAllByCreatedDate(LocalDateTime time);//todo
    List<StudentEntity> findAllByCreatedDateBetween(LocalDateTime fromDate, LocalDateTime toDate);
    Page<StudentEntity> findAllByName(String name, Pageable pageable);

    Page<StudentEntity> findAllByLevel(Integer level, Pageable pageable);

    Page<StudentEntity> findAllByGender(String gender, Pageable pageable);

    StudentEntity findByNameAndSurname(String name, String surname);

    // from StudentEntity where name =:name or phone =:phone
//    StudentEntity findByNameOrPhone(String name, String phone);

    // from StudentEntity where age between :ageFrom and :ageTo
    List<StudentEntity> findAllByAgeBetween(Integer ageFrom, Integer ageTo);

    // from StudentEntity where age between :ageFrom and :ageTo
//    List<StudentEntity> findAllByCreatedDateBetween(LocalDateTime dateFrom, LocalDateTime dateTo);
    // --  01.04.2023 00:00:00
    // 01.04.2023 12:50
    // 02.04.2023 12:50
    // 03.04.2023 12:50
    // 04.04.2023 12:50
    // --  04.04.2023 23:59:00
    // 05.04.2023 12:50

    List<StudentEntity> findByNameOrSurnameLikeOrAgeBetween(String name, String surname
            , Integer ageFrom, Integer ageTo);

    // from StudentEntity where name =:name order by age
    List<StudentEntity> findByNameOrderByAge(String name);

    // from StudentEntity where name =:name order by age
    List<StudentEntity> findTopByNameOrderByAge(String name); // limit 1

    List<StudentEntity> findFirstByNameOrderByAge(String name); // limit 1

    List<StudentEntity> findTop3ByNameOrderByAge(String name); // limit 3

    List<StudentEntity> findTop10ByNameOrderByAge(String name); // limit 10


//    @Transactional
//    @Modifying
//    @Query("update StudentEntity set visible = :visible where id = :sId")
//    Integer changeVisibility(@Param("sid") Integer sId, @Param("visible") Boolean v);
//    @Query("FROM StudentEntity where name like ?1")
//    List<StudentEntity> findByName2(String name);
//    @Query("SELECT new StudentEntity (id, name, surname) from StudentEntity ")
//    List<StudentEntity> findByName3();
//    @Query("SELECT new com.example.mapper.StudentMapper(id,name, phone) FROM StudentEntity ")
//    List<StudentMapper> findByName5();

    // 123  124
    // select * from student where phone in (......)
//    List<StudentEntity> findAllByPhoneIn(List<String> phoneList);

}
