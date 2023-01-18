package com.javalab.java_lab.dao;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "departments", uniqueConstraints = {
    //@UniqueConstraint(columnNames = {"department_name"})
})
@Data
public class DepartmentEntity {
    
    @Id
    @Column(name = "department_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "department_name", nullable = false)
    private String name;

    @Column(name = "department_description")
    private String description;

    @Column(name = "department_location")
    private String location;

}
