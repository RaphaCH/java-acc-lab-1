package com.javalab.java_lab.mapper;

import com.javalab.java_lab.dao.Department;
import com.javalab.java_lab.model.DepartmentDto;

public class DepartmentMapper {
    

    public static DepartmentDto toDepartmentDto(Department department) {
        if(department == null) return null;
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        departmentDto.setDescription(department.getDescription());
        departmentDto.setLocation(department.getLocation());
        return departmentDto;
    }

    public static Department toDepartment(DepartmentDto departmentDto) {
        if(departmentDto == null) return null;
        Department department = new Department();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());
        department.setDescription(departmentDto.getDescription());
        department.setLocation(departmentDto.getLocation());
        return department;
    }
}
