package com.javalab.java_lab.mapper;

import com.javalab.java_lab.dao.Employee;
import com.javalab.java_lab.model.EmployeeDto;

public class EmployeeMapper {
    

    public static EmployeeDto toEmployeeDto(Employee employee) {
        if(employee == null) return null;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setAge(employee.getAge());
        employeeDto.setJobTitle(employee.getJobTitle());
        employeeDto.setDepartment(DepartmentMapper.toDepartmentDto(employee.getDepartment()));
        return employeeDto;
    }

    public static Employee toEmployee(EmployeeDto employeeDto) {
        if(employeeDto == null) return null;
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setSalary(employeeDto.getSalary());
        employee.setAge(employeeDto.getAge());
        employee.setJobTitle(employeeDto.getJobTitle());
        employee.setDepartment(DepartmentMapper.toDepartment(employeeDto.getDepartment()));
        return employee;
    }

}
