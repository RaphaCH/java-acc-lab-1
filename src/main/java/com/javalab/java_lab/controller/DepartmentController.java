package com.javalab.java_lab.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javalab.java_lab.mapper.ErrorMessageMapper;
import com.javalab.java_lab.model.CustomException;
import com.javalab.java_lab.model.Department;
import com.javalab.java_lab.model.ErrorMessage;
import com.javalab.java_lab.model.Response;
import com.javalab.java_lab.service.DepartmentServices;

import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentServices departmentServices;

    

    @GetMapping()
    public ResponseEntity<?> getAllDepartments() {
        List<Department> response = departmentServices.retrieveAllDepartments();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createDepartment(@org.springframework.web.bind.annotation.RequestBody @Valid Department department ) {
        try {
            Department response = departmentServices.createNewDeparment(department);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (CustomException e) {
            ErrorMessage errorMessage = ErrorMessageMapper.toErrorMessage(e);
            HttpStatus status = HttpStatus.valueOf(Integer.parseInt(errorMessage.getStatusCode()));
            return new ResponseEntity<>(errorMessage, status);
        }
        
        
    }
}
