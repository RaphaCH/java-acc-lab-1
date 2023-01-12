package com.javalab.java_lab.model;

import com.javalab.java_lab.dao.Employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
    
    private Long id;

    @NotBlank(message = "Providing a first name is mandatory")
    private String firstName;

    @NotBlank(message = "Providing a last name is mandatory")
    private String lastName;

    @NotNull(message = "Providing a salary value is mandatory. It can be a number with decimals.")
    private Double salary;

    @NotNull(message = "Providing an age value is mandatory. It must be a number")
    private Integer age;

    private String jobTitle;

    private DepartmentDto department;

    @Builder
    public EmployeeDto(String firstName, String lastName, Double salary, Integer age, String jobTitle, DepartmentDto department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.age = age;
        this.jobTitle = jobTitle;
        this.department = department;
    }


    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.salary = employee.getSalary();
        this.age = employee.getAge();
        this.jobTitle = employee.getJobTitle();
    }
}
