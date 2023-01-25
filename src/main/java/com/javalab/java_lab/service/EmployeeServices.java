package com.javalab.java_lab.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalab.java_lab.dao.DepartmentEntity;
import com.javalab.java_lab.dao.DepartmentRepository;
import com.javalab.java_lab.dao.EmployeeEntity;
import com.javalab.java_lab.dao.EmployeeRepository;
import com.javalab.java_lab.mapper.EmployeeMapper;

// import lombok.extern.slf4j.Slf4j;

import com.javalab.java_lab.model.CustomException;
import com.javalab.java_lab.model.Employee;

@Service
// @Slf4j
public class EmployeeServices {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(EmployeeServices.class);

    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<Employee> employees = employeeEntities
                .stream()
                .map(employee -> EmployeeMapper.toEmployee(employee))
                .collect(Collectors.toList());
        return employees;
    }   

    public Employee getOneEmployee(long id) throws CustomException {
        log.info("Getting one employee, {}", id);
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            EmployeeEntity foundEmployee = optionalEmployee.get();
            Employee employee = EmployeeMapper.toEmployee(foundEmployee);
            // return new Response(true, "Success", HttpStatus.OK, employeeDto);
            return employee;
        } else {
            throw new CustomException("404", "Not_Found", "404", "Employee with id " + id + " not found in the database");
        }
    }

    public Employee createNewEmployee(Employee employee) {
        EmployeeEntity employeeEntity = EmployeeMapper.toEmployeeEntity(employee);
        employeeRepository.save(employeeEntity);
        Employee createdEmployee = EmployeeMapper.toEmployee(employeeEntity);
        return createdEmployee;
        // return new Response(true, "201 - Employee created", HttpStatus.CREATED, employee);
    }

    public String deleteOneEmployee(long id) throws CustomException {
        boolean exists = employeeRepository.existsById(id);
        if (exists) {
            employeeRepository.deleteById(id);
            return "Employee with id " + id + " deleted successfully";
        } else {
            throw new CustomException("404", "Not_Found", "404", "Employee with id " + id + " not found in the database");
        }
    }

    public Employee updateOneEmployee(Long id, Long dptId, Employee employee) throws CustomException {
        log.info("Updating one employee, {}, dpt id = {}", id, dptId);
        EmployeeEntity employeeEntity = EmployeeMapper.toEmployeeEntity(employee);
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            EmployeeEntity foundEmployee = optionalEmployee.get();
            foundEmployee.setFirstName(employeeEntity.getFirstName());
            foundEmployee.setLastName(employeeEntity.getLastName());
            foundEmployee.setSalary(employeeEntity.getSalary());
            foundEmployee.setAge(employeeEntity.getAge());
            foundEmployee.setJobTitle(employeeEntity.getJobTitle());
            if (dptId != null) {
                Optional<DepartmentEntity> optionalDepartment = departmentRepository.findById(dptId);
                if (optionalDepartment.isPresent()) {
                    DepartmentEntity foundDepartment = optionalDepartment.get();
                    foundEmployee.setDepartment(foundDepartment);
                }
            }
            employeeRepository.save(foundEmployee);
            Employee updatedEmployee = EmployeeMapper.toEmployee(foundEmployee);
            return updatedEmployee;
        } else {
            throw new CustomException("404", "Not_Found", "404", "Employee with id " + id + " not found in the database");
        }
    }
}
