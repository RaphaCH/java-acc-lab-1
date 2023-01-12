package com.javalab.java_lab.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.javalab.java_lab.dao.DepartmentRepository;
import com.javalab.java_lab.dao.Employee;
import com.javalab.java_lab.dao.EmployeeRepository;
import com.javalab.java_lab.model.DepartmentDto;
import com.javalab.java_lab.model.EmployeeDto;
import com.javalab.java_lab.model.ErrorMessage;

@Service
public class EmployeeServices {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream().map(EmployeeDto::new).toList();
    }

    // public ResponseEntity<?> getOneEmployee(long id) {
    //     Optional<EmployeePojo> optionalEmployee = employeeRepository.findById(id);
    //     if (optionalEmployee.isPresent()) {
    //         EmployeePojo foundEmployee = optionalEmployee.get();
    //         return new ResponseEntity<EmployeePojo>(foundEmployee, HttpStatus.OK);
    //     } else {
    //         ErrorMessage error = new ErrorMessage();
    //         error.setErrorCode("404");
    //         error.setDetails("user was not found");
    //         return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    //     }
    // }

    // public ResponseEntity<EmployeePojo> createNewEmployee(EmployeePojo employee) {
    //     // get validation to work
    //     // Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
    //     Long id = employee.getDepartmentId();
    //     if (id != null) {
    //         Optional<DepartmentPojo> optionalDepartment = departmentRepository.findById(id);
    //         if (optionalDepartment.isPresent()) {
    //             DepartmentPojo department = optionalDepartment.get();
    //             // EmployeePojo newEmployee = new EmployeePojo(employee.getFirstName(),
    //             // employee.getLastName(),
    //             // employee.getSalary(), employee.getAge(), employee.getJobTitle(), department);
    //             EmployeePojo newEmployee = new EmployeePojo(employee.getFirstName(),
    //                     employee.getLastName(),
    //                     employee.getSalary(),
    //                     employee.getAge(),
    //                     employee.getJobTitle(),
    //                     department);
    //             employeeRepository.save(newEmployee);
    //             // System.out.println(newEmployee);
    //             return new ResponseEntity<EmployeePojo>(employee, HttpStatus.CREATED);
    //         }
    //     }
    //     employeeRepository.save(employee);
    //     System.out.println(employee);
    //     return new ResponseEntity<EmployeePojo>(employee, HttpStatus.CREATED);
    // }

    // public ResponseEntity<?> deleteOneEmployee(long id) {
    //     boolean exists = employeeRepository.existsById(id);
    //     if (!exists) {
    //         return new ResponseEntity<String>("{ \"errorCode\": \"404\", " +
    //                 "  \"errorMessage\": \"No Employee was found with the given id.\", " +
    //                 "  \"subCode\": \"Oracle error code if any or any other error\", " +
    //                 "  \"details\": \"error description from oracle if any or other error\" }", HttpStatus.NOT_FOUND);
    //     } else {
    //         employeeRepository.deleteById(id);
    //         return new ResponseEntity<String>("Employee with id " + id + " has been deleted", HttpStatus.OK);
    //     }
    // }

    // public ResponseEntity<?> updateOneEmployee(Long id, EmployeePojo employee) {
    //     Optional<EmployeePojo> optionalEmployee = employeeRepository.findById(id);
    //     if (optionalEmployee.isPresent()) {
    //         EmployeePojo foundEmployee = optionalEmployee.get();
    //         if (employee.getDepartmentId() != null) {
    //             Long deptId = employee.getDepartmentId();
    //             Optional<DepartmentPojo> optionalDepartment = departmentRepository.findById(deptId);
    //             if (optionalDepartment.isPresent()) {
    //                 DepartmentPojo department = optionalDepartment.get();
    //                 foundEmployee.setDepartment(department);
    //             }
    //         }
    //         if (employee.getFirstName() != null && employee.getFirstName().length() > 0
    //                 && employee.getFirstName() != foundEmployee.getFirstName()) {
    //             foundEmployee.setFirstName(employee.getFirstName());
    //         }
    //         if (employee.getLastName() != null && employee.getLastName().length() > 0
    //                 && employee.getLastName() != foundEmployee.getLastName()) {
    //             foundEmployee.setLastName(employee.getLastName());
    //         }
    //         if (employee.getSalary() != null && employee.getSalary() != 0
    //                 && employee.getSalary() != foundEmployee.getSalary()) {
    //             foundEmployee.setSalary(employee.getSalary());
    //         }
    //         if (employee.getJobTitle() != null && employee.getJobTitle().length() > 0
    //                 && employee.getJobTitle() != foundEmployee.getJobTitle()) {
    //             foundEmployee.setJobTitle(employee.getJobTitle());
    //         }
    //         if (employee.getAge() != null && employee.getAge() > 0 && employee.getAge() != foundEmployee.getAge()) {
    //             foundEmployee.setAge(employee.getAge());
    //         }
    //         employeeRepository.save(foundEmployee);
    //         return new ResponseEntity<EmployeePojo>(foundEmployee, HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<String>("{ \"errorCode\": \"404\", " +
    //                 "  \"errorMessage\": \"No Employee was found with the given id.\", " +
    //                 "  \"subCode\": \"Oracle error code if any or any other error\", " +
    //                 "  \"details\": \"error description from oracle if any or other error\" }", HttpStatus.NOT_FOUND);
    //     }
    // }
}
