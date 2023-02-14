package com.javalab.java_lab.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalab.java_lab.dao.DepartmentEntity;
import com.javalab.java_lab.dao.DepartmentRepository;
import com.javalab.java_lab.mapper.DepartmentMapper;
import com.javalab.java_lab.model.CustomException;
import com.javalab.java_lab.model.Department;

@Service
public class DepartmentServices {

    private static final Logger log = LoggerFactory.getLogger(DepartmentServices.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> retrieveAllDepartments() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        List<Department> departments = departmentEntities
                .stream()
                .map(department -> DepartmentMapper.toDepartment(department))
                .collect(Collectors.toList());
        return departments;
    }

    public Department createNewDeparment(Department department) throws CustomException {
        DepartmentEntity departmentEntity = DepartmentMapper.toDepartmentEntity(department);
        String dptName = departmentEntity.getName();
        DepartmentEntity dptExists = departmentRepository.findDepartmentByName(dptName);
        if (dptExists == null) {
            log.info("{} department does not exist in the database, creating new department {}", dptName,
                    departmentEntity);
            departmentRepository.save(departmentEntity);
            Department newDepartment = DepartmentMapper.toDepartment(departmentEntity);
            return newDepartment;
        } else {
            throw new CustomException("409", "Conflict", "409", "Department " + dptName + " already exists in the database");
        }
    }


    public void deleteOneDepartment(long id) throws CustomException {
        boolean exists = departmentRepository.existsById(id);
        if (exists) {
            departmentRepository.deleteById(id);
        } else {
            throw new CustomException("404", "Not_Found", "404", "Department with id " + id + " not found in the database");
        }
    }

}
