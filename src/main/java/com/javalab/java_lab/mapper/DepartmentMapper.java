package com.javalab.java_lab.mapper;

import com.javalab.java_lab.dao.DepartmentEntity;
import com.javalab.java_lab.model.Department;

public class DepartmentMapper {
    

    public static Department toDepartment(DepartmentEntity department) {
        if(department == null) return null;
        Department departmentDto = new Department();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        departmentDto.setDescription(department.getDescription());
        departmentDto.setLocation(department.getLocation());
        return departmentDto;
    }

    public static DepartmentEntity toDepartmentEntity(Department departmentDto) {
        if(departmentDto == null) return null;
        DepartmentEntity department = new DepartmentEntity();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());
        department.setDescription(departmentDto.getDescription());
        department.setLocation(departmentDto.getLocation());
        return department;
    }
}
