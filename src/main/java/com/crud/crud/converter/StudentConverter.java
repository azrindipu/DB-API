package com.crud.crud.converter;

import com.crud.crud.dto.StudentDto;
import com.crud.crud.model.StudentModel;
import com.crud.crud.model.StudentModelMongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentConverter {
    private static final Logger logger = LoggerFactory.getLogger(StudentConverter.class);

    public StudentDto convertModelToDto(StudentModel studentModel) {
        return new StudentDto(String.valueOf(studentModel.getId()), studentModel.getInstitute(),
                studentModel.getStudentName(), studentModel.getStudentRoll());

    }

    public List<StudentDto> convertModelToDtoSql(List<StudentModel> studentModels) {
        List<StudentDto> studentDtos = new ArrayList<>();
        if (studentModels != null && studentModels.size() > 0) {
            for (StudentModel studentModel : studentModels) {
                studentDtos.add(new StudentDto(String.valueOf(studentModel.getId()), studentModel.getInstitute(),
                        studentModel.getStudentName(), studentModel.getStudentRoll()));
            }
        }
        return studentDtos;
    }

    public StudentDto convertModelToDto(StudentModelMongo studentModel) {
        return new StudentDto(String.valueOf(studentModel.getId()), studentModel.getInstitute(),
                studentModel.getStudentName(), studentModel.getStudentRoll());
    }

    public List<StudentDto> convertModelToDto(List<StudentModelMongo> studentModels) {
        List<StudentDto> studentDtos = new ArrayList<>();
        if (studentModels != null && studentModels.size() > 0) {
            for (StudentModelMongo studentModel : studentModels) {
                studentDtos.add(new StudentDto(String.valueOf(studentModel.getId()), studentModel.getInstitute(),
                        studentModel.getStudentName(), studentModel.getStudentRoll()));
            }
        }
        return studentDtos;
    }

    public StudentModelMongo convertDtoToModelMongo(StudentDto studentDto) {
        return new StudentModelMongo(studentDto.getId(), studentDto.getInstitute(),
                studentDto.getName(), studentDto.getRoll());
    }

    public StudentDto convertModelToDto(String institute, int number) {
        return new StudentDto(null, institute,
                institute + "_" + number, String.valueOf(number));

    }

    public StudentModel convertDtoToModel(StudentDto studentDto) {
        return new StudentModel(studentDto.getId() != null ? Integer.parseInt(studentDto.getId()) : null, studentDto.getInstitute(), studentDto.getName(), studentDto.getRoll());
    }
}
