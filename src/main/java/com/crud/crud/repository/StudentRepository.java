package com.crud.crud.repository;

import com.crud.crud.model.StudentModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("studentRepo")
public interface StudentRepository extends PagingAndSortingRepository<StudentModel, Integer> {
    List<StudentModel> findByStudentName(String name);

    List<StudentModel> findByInstitute(String institute);

    List<StudentModel> findByStudentRoll(String rool);

}
