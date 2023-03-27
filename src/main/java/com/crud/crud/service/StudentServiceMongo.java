package com.crud.crud.service;

import com.crud.crud.model.StudentModelMongo;
import com.crud.crud.repository.StudentRepositoryMongo;
import com.crud.crud.utils.Constants;
import com.crud.crud.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceMongo {
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceMongo.class);

    @Autowired
    private StudentRepositoryMongo studentRepositoryMongo;

    public StudentModelMongo createStudent(StudentModelMongo studentModel) {
        return studentRepositoryMongo.insert(studentModel);
    }

    public List<StudentModelMongo> getStudentByAmount(Integer amount, List<String> institutes) {
        Pageable pageable = PageRequest.of(0, amount);
        Page<StudentModelMongo> page = studentRepositoryMongo.findByInstituteIn(institutes, pageable);
        if (page != null && page.getContent().size() > 0) {
            return page.getContent();
        }
        return new ArrayList<>();
    }

    public long getTotalNumberOfData() {
        return studentRepositoryMongo.count();
    }

    public List<StudentModelMongo> getDataBySearchKeyValue(String key, String value) {
        if (key.equalsIgnoreCase(Constants.SEARCH_KEY_NAME)) return studentRepositoryMongo.findByStudentName(value);
        if (key.equalsIgnoreCase(Constants.SEARCH_KEY_INSTITUTE)) return studentRepositoryMongo.findByInstitute(value);
        return studentRepositoryMongo.findByStudentRoll(value);
    }

    public void clearPreviousData() {
        studentRepositoryMongo.deleteAll();
    }
}
