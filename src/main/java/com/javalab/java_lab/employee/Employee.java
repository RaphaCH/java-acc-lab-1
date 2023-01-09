package com.javalab.java_lab.employee;


import com.javalab.java_lab.department.Department;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employees", uniqueConstraints = {
    //@UniqueConstraint(name = "example_student_email_unique", columnNames = "example_column_name")
})
public class Employee {

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
    private String firstName;

    @Getter
    @Setter
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Getter
    @Setter
    @Column(name = "salary")
    private Double salary;

    @Getter
    @Setter
    @Column(name = "age", nullable = false)
    private Integer age;

    @Getter
    @Setter
    @Column(name = "job_title")
    private String jobTitle;

    @ManyToOne(targetEntity = Department.class)
    @Getter
    @Setter
    private Department department;

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

    public Employee(){}

    public Employee(String firstName, String lastName, Double salary, Integer age, String jobTitle, Department department){
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
