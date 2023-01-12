package com.javalab.java_lab.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javalab.java_lab.model.EmployeeDto;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    List<Employee> findByFirstNameAndLastName(String firstName, String LastName);

}
