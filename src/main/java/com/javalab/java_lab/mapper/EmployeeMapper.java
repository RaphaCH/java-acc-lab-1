package com.javalab.java_lab.mapper;

import com.javalab.java_lab.dao.EmployeeEntity;
import com.javalab.java_lab.model.Employee;

public class EmployeeMapper {
    

    public static Employee toEmployee(EmployeeEntity employeeEntity) {
        if(employeeEntity == null) return null;
        Employee employee = new Employee();
        employee.setId(employeeEntity.getId());
        employee.setFirstName(employeeEntity.getFirstName());
        employee.setLastName(employeeEntity.getLastName());
        employee.setSalary(employeeEntity.getSalary());
        employee.setAge(employeeEntity.getAge());
        employee.setJobTitle(employeeEntity.getJobTitle());
        employee.setDepartment(DepartmentMapper.toDepartment(employeeEntity.getDepartment()));
        return employee;
    }

    public static EmployeeEntity toEmployeeEntity(Employee employee) {
        if(employee == null) return null;
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(employee.getId());
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setSalary(employee.getSalary());
        employeeEntity.setAge(employee.getAge());
        employeeEntity.setJobTitle(employee.getJobTitle());
        employeeEntity.setDepartment(DepartmentMapper.toDepartmentEntity(employee.getDepartment()));
        return employeeEntity;
    }

}
