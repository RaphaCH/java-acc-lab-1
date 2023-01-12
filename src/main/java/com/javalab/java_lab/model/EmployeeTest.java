package com.javalab.java_lab.model;


import org.springframework.validation.annotation.Validated;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employees", uniqueConstraints = {
    //@UniqueConstraint(name = "example_student_email_unique", columnNames = "example_column_name")
})
@Validated
public class EmployeeTest {

    // Oracle db annotarion;
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    // Lombok for getter & setter readability;
    @Getter
    @Setter
    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "Providing a first name is mandatory")
    private String firstName;

    @Getter
    @Setter
    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Providing a last name is mandatory")
    private String lastName;

    @Getter
    @Setter
    @Column(name = "salary", nullable = false)
    @NotNull(message = "Providing a salary value is mandatory. It can be a number with decimals.")
    private Double salary;

    @Getter
    @Setter
    @Column(name = "age", nullable = false)
    @NotNull(message = "Providing an age value is mandatory. It must be a number")
    @Min(value = 18, message = "Age cannot be less than 18")
    private Integer age;

    @Getter
    @Setter
    @Column(name = "job_title")
    private String jobTitle;

    @ManyToOne(targetEntity = DepartmentDto.class)
    @Getter
    @Setter
    private DepartmentDto department;

    @Getter
    @Setter
    @Column(name = "department_id", nullable = true)
    private Long departmentId;

    // @Getter
    // @Setter
    // @Column(name = "department_name")
    // private String departmentName;

    // @Getter
    // @Setter
    // @Column(name = "department_location")
    // private String departmentLocation;

    // @Getter
    // @Setter
    // @Column(name = "department_description")
    // private String departmentDescription;

    public EmployeeTest(){}

    public EmployeeTest(String firstName, String lastName, Double salary, Integer age, String jobTitle, DepartmentDto department){
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.age = age;
        this.jobTitle = jobTitle;
        this.department = department;
    }

    @Override
    public String toString() {
        return String.format(
                "first name = %s | last name =  %s, salary = %s, age = %s, job title = %s | And belongs to the department = %s",
                firstName,
                lastName,
                salary,
                age,
                jobTitle,
                department);
    }

}
