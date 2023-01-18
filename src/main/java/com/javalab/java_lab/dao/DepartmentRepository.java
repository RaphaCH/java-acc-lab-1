package com.javalab.java_lab.dao;

import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {


    public DepartmentEntity findDepartmentByName(String name);  
}
