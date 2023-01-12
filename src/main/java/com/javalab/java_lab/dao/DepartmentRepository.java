package com.javalab.java_lab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javalab.java_lab.model.DepartmentDto;

public interface DepartmentRepository extends JpaRepository<DepartmentDto, Long> {


    public DepartmentDto findDepartmentByName(String name);    
}
