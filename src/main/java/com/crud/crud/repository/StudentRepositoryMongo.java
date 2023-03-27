package com.crud.crud.repository;

import com.crud.crud.model.StudentModelMongo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepositoryMongo extends MongoRepository<StudentModelMongo, String> {
    List<StudentModelMongo> findByStudentName(String name);

    List<StudentModelMongo> findByInstitute(String institute);

    List<StudentModelMongo> findByStudentRoll(String roll);
    Page findByInstituteIn(List<String> institutes, Pageable page);
}
