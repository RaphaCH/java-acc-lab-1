package com.javalab.java_lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
	title = "Employee | Department Java Spring Boot Lab",
	description = "A test project to get used to Spring Boot",
	version = "1.0.0"), tags = {@Tag(name = "Employee Api contoller", description = "This is the global CRUD controller for the Employee model")})
public class JavaLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaLabApplication.class, args);
	}

}
