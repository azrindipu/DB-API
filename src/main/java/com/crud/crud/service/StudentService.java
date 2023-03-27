package com.crud.crud.service;

import com.crud.crud.model.StudentModel;
import com.crud.crud.repository.StudentRepository;
import com.crud.crud.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    @Qualifier("studentRepo")
    private StudentRepository studentRepository;

    public StudentModel createStudent(StudentModel studentModel) {
        return studentRepository.save(studentModel);
    }

    public List<StudentModel> getStudentByAmount(Integer amount) {
        Pageable pageable = PageRequest.of(0, amount);
        Page<StudentModel> page = studentRepository.findAll(pageable);
        if (page != null && page.getContent().size() > 0) {
            return page.getContent();
        }
        return new ArrayList<>();
    }

    public long getTotalNumberOfData() {
        return studentRepository.count();
    }

    public List<StudentModel> getDataBySearchKeyValue(String key, String value) {
        if (key.equalsIgnoreCase(Constants.SEARCH_KEY_NAME)) return studentRepository.findByStudentName(value);
        if (key.equalsIgnoreCase(Constants.SEARCH_KEY_INSTITUTE)) return studentRepository.findByInstitute(value);
        return studentRepository.findByStudentRoll(value);
    }
}
