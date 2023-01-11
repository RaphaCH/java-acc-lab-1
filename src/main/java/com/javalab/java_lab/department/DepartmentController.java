package com.javalab.java_lab.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentServices departmentServices;

    

    @GetMapping("/all")
    public List<Department> getAllDepartments() {
        return departmentServices.retrieveAllDepartments();
    }

    @PostMapping("/createDepartment")
    public Department createDepartment(@org.springframework.web.bind.annotation.RequestBody Department department ) {
        departmentServices.createNewDeparment(department);
        return department;
    }
}
