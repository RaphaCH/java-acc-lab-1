package com.javalab.java_lab.dao;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employees", uniqueConstraints = {
    //@UniqueConstraint(name = "example_student_email_unique", columnNames = "example_column_name")
})
@Getter
@Setter
public class Employee {

    // Oracle db annotarion;
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Lombok for getter & setter readability;
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "salary", nullable = false)
    private Double salary;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "job_title")
    private String jobTitle;

    // @ManyToOne(targetEntity = Department.class)
    // private Department department;

    @ManyToOne(targetEntity = Department.class)
    private Department department;

    @Column(name = "department_id", nullable = true)
    private Long departmentId;

}
