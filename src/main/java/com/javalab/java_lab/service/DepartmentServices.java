package com.javalab.java_lab.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.javalab.java_lab.dao.DepartmentEntity;
import com.javalab.java_lab.dao.DepartmentRepository;
import com.javalab.java_lab.mapper.DepartmentMapper;
import com.javalab.java_lab.model.Department;

import com.javalab.java_lab.model.Response;


import jakarta.persistence.EntityExistsException;

@Service
public class DepartmentServices {
    
    private static final Logger log = LoggerFactory.getLogger(DepartmentServices.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    public Response createNewDeparment(Department department) {
        DepartmentEntity departmentEntity = DepartmentMapper.toDepartmentEntity(department);
        String dptName = departmentEntity.getName();
        DepartmentEntity dptExists =  departmentRepository.findDepartmentByName(dptName);
        if(dptExists == null) {
            log.info("{} department does not exist in the database, creating new department {}", dptName, departmentEntity);
            departmentRepository.save(departmentEntity);
            return new Response(true, "201 - Department Created", HttpStatus.CREATED, department);
        } else {
            EntityExistsException e = new EntityExistsException("department " + dptName + " already exists in the database");
            log.error("department {} already exists in the database, which throws {}", dptName, e.getClass());
            return new Response(false, "409 - Department already exists", HttpStatus.CONFLICT, null);
        }
    }

    public Response retrieveAllDepartments() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        List<Department> departments = departmentEntities
                .stream()
                .map(department -> DepartmentMapper.toDepartment(department))
                .collect(Collectors.toList());
        return new Response(true, "200 - All Departments retrieved", HttpStatus.OK, departments);
    }


    
}
