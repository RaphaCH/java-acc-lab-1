package com.javalab.java_lab.employee;

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
    public Employee(){}
    public Employee(String firstName, String lastName, Double salary, Integer age, String jobTitle){
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.age = age;
        this.jobTitle = jobTitle;
    }
    @Override
    public String toString() {
        return String.format(
                "first name = %s | last name =  %s, salary = %s, age = %s, job title = %s",
                firstName,
                lastName,
                salary,
                age,
                jobTitle);
    }

}
