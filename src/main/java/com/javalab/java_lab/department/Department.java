package com.javalab.java_lab.department;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "departments", uniqueConstraints = {
    //@UniqueConstraint(columnNames = {"department_name"})
})
public class Department {
    
    @Id
    @Column(name = "department_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Column(name = "department_name", nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(name = "department_description")
    @Getter
    @Setter
    private String description;

    @Column(name = "department_location")
    @Getter
    @Setter
    private String location;

    // @OneToMany(targetEntity = Employee.class)
    // private List employees;

    public Department(){}

    public Department(String name, String description, String location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }

    @Override
    public String toString() {
        return String.format(
            "department name = %s | department description = %s |  department location = %s",
             name,
             description,
             location);
    }

}
