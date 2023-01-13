package com.javalab.java_lab.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
   
    private Long id;
    @NotBlank(message = "Department name is mandatory")
    private String name;
    private String description;
    private String location;

}
