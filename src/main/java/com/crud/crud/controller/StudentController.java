package com.crud.crud.controller;

import com.crud.crud.dto.StatusDto;
import com.crud.crud.dto.StudentDto;
import com.crud.crud.manager.StudentManager;
import com.crud.crud.utils.Constants;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentManager studentManager;

    @PostMapping(value = "/student/{amount}")
    public ResponseEntity<JSONObject> createStudent(@PathVariable(required = true) Integer amount) {
        StatusDto result = null;
        try {
            result = studentManager.createStudent(amount);
        } catch (Exception e) {
            e.printStackTrace();

        }
        JSONObject responseBody = new JSONObject();
        responseBody.put(Constants.RESPONSE_BODY_DATA, result);
        responseBody.put(Constants.RESPONSE_BODY_STATUS, HttpStatus.OK);

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping(value = "/student/{amount}/{dbType}")
    public ResponseEntity<JSONObject> getStudentByAmount(@PathVariable(required = true) Integer amount,
                                                         @PathVariable(required = true) String dbType) {
        List<StudentDto> result = null;
        try {
            result = studentManager.getStudentByAmount(amount, dbType);
        } catch (Exception e) {
            e.printStackTrace();

        }
        JSONObject responseBody = new JSONObject();
        responseBody.put(Constants.RESPONSE_BODY_DATA, result);
        responseBody.put(Constants.RESPONSE_BODY_STATUS, HttpStatus.OK);

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping(value = "/student/status/{dbType}")
    public ResponseEntity<JSONObject> getDbStatus(@PathVariable(required = true) String dbType) {
        StatusDto result = null;
        try {
            result = studentManager.getDbStatus(dbType);
        } catch (Exception e) {
            e.printStackTrace();

        }
        JSONObject responseBody = new JSONObject();
        responseBody.put(Constants.RESPONSE_BODY_DATA, result);
        responseBody.put(Constants.RESPONSE_BODY_STATUS, HttpStatus.OK);

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping(value = "/student/{searchKey}/{searchValue}/{dbType}")
    public ResponseEntity<JSONObject> getDbStatus(@PathVariable(required = true) String searchKey,
                                                  @PathVariable(required = true) String searchValue,
                                                  @PathVariable(required = true) String dbType) {
        List<StudentDto> result = null;
        try {
            result = studentManager.getDataBySearchKeyValue(searchKey, searchValue, dbType);
        } catch (Exception e) {
            e.printStackTrace();

        }
        JSONObject responseBody = new JSONObject();
        responseBody.put(Constants.RESPONSE_BODY_DATA, result);
        responseBody.put(Constants.RESPONSE_BODY_STATUS, HttpStatus.OK);

        return ResponseEntity.ok(responseBody);
    }
}
