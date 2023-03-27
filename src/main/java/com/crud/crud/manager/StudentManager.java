package com.crud.crud.manager;

import com.crud.crud.converter.StudentConverter;
import com.crud.crud.dto.StatusDto;
import com.crud.crud.dto.StudentDto;
import com.crud.crud.model.StudentModel;
import com.crud.crud.model.StudentModelMongo;
import com.crud.crud.service.StudentService;
import com.crud.crud.service.StudentServiceMongo;
import com.crud.crud.utils.Constants;
import com.crud.crud.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentManager {
    private static final Logger logger = LoggerFactory.getLogger(StudentManager.class);

    @Value("${institute.names}")
    private String institutesString;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlValue;

    private List<String> institutes = null;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentServiceMongo studentServiceMongo;

    @Autowired
    private StudentConverter studentConverter;

    @PostConstruct
    public void preLoad() {
        if (institutesString == null || institutesString.isEmpty()) {
            institutesString = Constants.INSTITURE_NAMES;
        }
        if (ddlValue != null && ddlValue.trim().equalsIgnoreCase("create")) {
            studentServiceMongo.clearPreviousData();
        }
        if (institutesString != null && institutes == null) {
            institutes = new ArrayList<>();
            try {
                String[] array = institutesString.split(",");
                if (array != null && array.length > 0) {
                    for (String institute : array) {
                        if (institute != null && !institute.trim().isEmpty()) {
                            institutes.add(institute);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public StatusDto createStudent(Integer amount) {
        int count = 0;
        for (String institute : institutes) {
            for (int i = 0; i < amount; i++) {
                StudentDto studentDto = studentConverter.convertModelToDto(institute, i);
                studentService.createStudent(studentConverter.convertDtoToModel(studentDto));
                studentServiceMongo.createStudent(studentConverter.convertDtoToModelMongo(studentDto));
                count++;
                logger.info("count: " + count);
            }
        }
        return new StatusDto(amount, null, null);
    }

    public List<StudentDto> getStudentByAmount(Integer amount, String dbType) {
        List<StudentDto> studentDtos = new ArrayList<>();
        List<StudentModel> studentModels = new ArrayList<>();
        List<StudentModelMongo> studentModelMongos = new ArrayList<>();
        dbType = Utils.checkDbType(dbType);
        if (dbType.equalsIgnoreCase(Constants.DB_TYPE_MY_SQL)) {
            studentModels.addAll(studentService.getStudentByAmount(amount));
            if (studentModels != null && studentModels.size() > 0) {
                for (StudentModel studentModel : studentModels) {
                    studentDtos.add(studentConverter.convertModelToDto(studentModel));
                }
            }
        } else {
            List<String> list = new ArrayList<>();
            list.add(Utils.getRandomInstitute(institutes));
            list.add(Utils.getRandomInstitute(institutes));
            studentModelMongos.addAll(studentServiceMongo.getStudentByAmount(amount, list));
            if (studentModelMongos != null && studentModelMongos.size() > 0) {
                for (StudentModelMongo studentModelMongo : studentModelMongos) {
                    studentDtos.add(studentConverter.convertModelToDto(studentModelMongo));
                }
            }
        }
        return studentDtos;
    }

    public StatusDto getDbStatus(String dbType) {
        dbType = Utils.checkDbType(dbType);
        StatusDto statusDto = new StatusDto();
        if (dbType.equalsIgnoreCase(Constants.DB_TYPE_MY_SQL)) {
            statusDto.setDbType(Constants.DB_TYPE_MY_SQL);
            statusDto.setAmountOfData(studentService.getTotalNumberOfData());
        } else {
            statusDto.setDbType(Constants.DB_TYPE_MONGODB);
            statusDto.setAmountOfData(studentServiceMongo.getTotalNumberOfData());
        }
        return statusDto;
    }

    public List<StudentDto> getDataBySearchKeyValue(String searchKey, String searchValue, String dbType) {
        List<StudentDto> studentDtos = new ArrayList<>();
        dbType = Utils.checkDbType(dbType);
        if (Utils.checkSearchKey(searchKey)) {
            if (dbType.equalsIgnoreCase(Constants.DB_TYPE_MY_SQL)) {
                studentDtos.addAll(studentConverter.convertModelToDtoSql(studentService.getDataBySearchKeyValue(searchKey,
                        searchValue)));
            } else {
                studentDtos.addAll(studentConverter.convertModelToDto(
                        studentServiceMongo.getDataBySearchKeyValue(searchKey, searchValue)));
            }
        }
        return studentDtos;
    }


}
