package com.javalab.java_lab.dao;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "departments", uniqueConstraints = {
    //@UniqueConstraint(columnNames = {"department_name"})
})
public class Department {
    
    @Id
    @Column(name = "department_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "department_name", nullable = false)
    @NotNull(message = "A department name must be provided")
    private String name;

    @Column(name = "department_description")
    private String description;

    @Column(name = "department_location")
    private String location;

}
