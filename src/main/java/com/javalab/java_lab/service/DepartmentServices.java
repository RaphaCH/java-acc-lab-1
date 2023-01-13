// package com.javalab.java_lab.service;

// import java.util.List;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.javalab.java_lab.dao.Department;
// import com.javalab.java_lab.dao.DepartmentRepository;
// import com.javalab.java_lab.model.DepartmentDto;

// import jakarta.persistence.EntityExistsException;

// @Service
// public class DepartmentServices {
    
//     private static final Logger log = LoggerFactory.getLogger(DepartmentServices.class);

//     @Autowired
//     private DepartmentRepository departmentRepository;

//     public void createNewDeparment(DepartmentDto department) {
//         String dptName = department.getName();
//         Department dptExists =  departmentRepository.findDepartmentByName(dptName);
//         if(dptExists == null) {
//             log.info("{} department does not exist in the database, creating new department {}", dptName, department);
//             departmentRepository.save(department);
//         } else {
//             EntityExistsException e = new EntityExistsException("department " + dptName + " already exists in the database");
//             log.error("department {} already exists in the database, which throws {}", dptName, e.getClass());
//             throw e;
//         }
//     }

//     public List<DepartmentDto> retrieveAllDepartments() {
//        return departmentRepository.findAll();
//     }


    
// }
