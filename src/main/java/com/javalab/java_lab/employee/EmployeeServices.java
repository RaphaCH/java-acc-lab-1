package com.javalab.java_lab.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServices {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    public Optional<Employee> getOneEmployee(long id) {
        return employeeRepository.findById(id);
    }

    public Employee createNewEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public void deleteOneEmployee(long id) {
        employeeRepository.deleteById(id);
    }


}
