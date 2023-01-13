package com.javalab.java_lab.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.javalab.java_lab.dao.Department;
import com.javalab.java_lab.dao.DepartmentRepository;
import com.javalab.java_lab.dao.Employee;
import com.javalab.java_lab.dao.EmployeeRepository;
import com.javalab.java_lab.mapper.DepartmentMapper;
import com.javalab.java_lab.mapper.EmployeeMapper;
import com.javalab.java_lab.model.Response;
import com.javalab.java_lab.model.EmployeeDto;
import com.javalab.java_lab.model.ErrorMessage;

@Service
public class EmployeeServices {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(EmployeeServices.class);

    public Response getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = employees
                .stream()
                .map(employee -> EmployeeMapper.toEmployeeDto(employee))
                .collect(Collectors.toList());
        return new Response(true, "200 - All Employees retrieved", HttpStatus.OK, employeeDtos);
    }

    public Response getOneEmployee(long id) {
        log.info("Getting one employee, {}", id);
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee foundEmployee = optionalEmployee.get();
            EmployeeDto employeeDto = EmployeeMapper.toEmployeeDto(foundEmployee);
            return new Response(true, "Success", HttpStatus.OK, employeeDto);
        } else {
            return new Response(false, "404 - Employee not found", HttpStatus.NOT_FOUND, null);
        }
    }

    public Response createNewEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.toEmployee(employeeDto);
        employeeRepository.save(employee);
        return new Response(true, "201 - Employee created", HttpStatus.CREATED, employeeDto);
    }

    public Response deleteOneEmployee(long id) {
        boolean exists = employeeRepository.existsById(id);
        if (!exists) {
            return new Response(false, "404 - Employee not found", HttpStatus.NOT_FOUND, null);
        } else {
            employeeRepository.deleteById(id);
            return new Response(true, "200 - Employee deleted", HttpStatus.OK, null);
        }
    }

    public Response updateOneEmployee(Long id, Long dptId, EmployeeDto employeeDto) {
        log.info("Updating one employee, {}, dpt id = {}", id, dptId);
        Employee employee = EmployeeMapper.toEmployee(employeeDto);
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee foundEmployee = optionalEmployee.get();
            foundEmployee.setFirstName(employee.getFirstName());
            foundEmployee.setLastName(employee.getLastName());
            foundEmployee.setSalary(employee.getSalary());
            foundEmployee.setAge(employee.getAge());
            foundEmployee.setJobTitle(employee.getJobTitle());
            if (dptId != null) {
                Optional<Department> optionalDepartment = departmentRepository.findById(dptId);
                if (optionalDepartment.isPresent()) {
                    Department foundDepartment = optionalDepartment.get();
                    foundEmployee.setDepartment(foundDepartment);
                }
            }
            employeeRepository.save(foundEmployee);
            EmployeeDto updatedEmployeeDto = EmployeeMapper.toEmployeeDto(foundEmployee);
            return new Response(true, "Employee updated successfully", HttpStatus.OK, updatedEmployeeDto);
        } else {
            return new Response(false, "404 - Employee not found", HttpStatus.NOT_FOUND, null);
        }
    }
}
