package com.javalab.java_lab.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityExistsException;

@Service
public class DepartmentServices {
    

    @Autowired
    private DepartmentRepository departmentRepository;

    public void createNewDeparment(Department department) {
        String dptName = department.getName();
        Department dptExists =  departmentRepository.findDepartmentByName(dptName);
        if(dptExists == null) {
            departmentRepository.save(department);
        } else {
            throw new EntityExistsException("department " + dptName + " already exists in the database");
        }
    }

    public List<Department> retrieveAllDepartments() {
       return departmentRepository.findAll();
    }


    
}
