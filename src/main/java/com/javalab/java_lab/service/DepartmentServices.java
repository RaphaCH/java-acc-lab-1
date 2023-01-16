package com.javalab.java_lab.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.javalab.java_lab.dao.Department;
import com.javalab.java_lab.dao.DepartmentRepository;
import com.javalab.java_lab.mapper.DepartmentMapper;
import com.javalab.java_lab.model.DepartmentDto;

import com.javalab.java_lab.model.Response;


import jakarta.persistence.EntityExistsException;

@Service
public class DepartmentServices {
    
    private static final Logger log = LoggerFactory.getLogger(DepartmentServices.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    public Response createNewDeparment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.toDepartment(departmentDto);
        String dptName = department.getName();
        Department dptExists =  departmentRepository.findDepartmentByName(dptName);
        if(dptExists == null) {
            log.info("{} department does not exist in the database, creating new department {}", dptName, department);
            departmentRepository.save(department);
            return new Response(true, "201 - Department Created", HttpStatus.CREATED, departmentDto);
        } else {
            EntityExistsException e = new EntityExistsException("department " + dptName + " already exists in the database");
            log.error("department {} already exists in the database, which throws {}", dptName, e.getClass());
            return new Response(false, "409 - Department already exists", HttpStatus.CONFLICT, null);
        }
    }

    public Response retrieveAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDto> departmentDtos = departments
                .stream()
                .map(department -> DepartmentMapper.toDepartmentDto(department))
                .collect(Collectors.toList());
        return new Response(true, "200 - All Departments retrieved", HttpStatus.OK, departmentDtos);
    }


    
}
