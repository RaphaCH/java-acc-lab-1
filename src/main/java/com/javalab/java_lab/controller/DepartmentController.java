// package com.javalab.java_lab.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.javalab.java_lab.model.DepartmentDto;
// import com.javalab.java_lab.service.DepartmentServices;


// @RestController
// @CrossOrigin(origins = "http://localhost:8080")
// @RequestMapping("/departments")
// public class DepartmentController {

//     @Autowired
//     private DepartmentServices departmentServices;

    

//     @GetMapping("/all")
//     public List<DepartmentDto> getAllDepartments() {
//         return departmentServices.retrieveAllDepartments();
//     }

//     @PostMapping("/createDepartment")
//     public DepartmentDto createDepartment(@org.springframework.web.bind.annotation.RequestBody DepartmentDto department ) {
//         departmentServices.createNewDeparment(department);
//         return department;
//     }
// }
