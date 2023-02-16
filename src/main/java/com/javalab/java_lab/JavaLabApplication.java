package com.javalab.java_lab;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@OpenAPIDefinition(info = @Info(
	title = "Employee | Department Java Spring Boot Lab",
	description = "A test project to get used to Spring Boot",
	version = "1.0.0"), tags = {@Tag(name = "Employee Api contoller", description = "This is the global CRUD controller for the Employee model")})
public class JavaLabApplication {

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(JavaLabApplication.class);

	public static void main(String[] args) {
		log.info("Starting Java Lab Application");
		SpringApplication.run(JavaLabApplication.class, args);
		log.info("Java Lab Application Started");
	}

	// TODOs / Update:
	// Create Employee should bind employee to department immediately - DONE 
	// Implement Aspect Logging in this project too - DONE
	// Implement ErrorMessage Enum in error returns
	// Align ErrorHandling in both projects - DONE
	// Implement @Transactional in both projects - Basic Impl DONE
	// DeleteOne in Employee/Department returning String is a problem in Task API - DONE
	// Re-implement test classes in both projects 

}
