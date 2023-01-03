package com.javalab.java_lab.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.sym.Name;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api") 

public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @GetMapping("/all")
    //The operation annotation is method specific. 
    //The TAG argument could override the default organization of every CRUD operation being held under same controller class
    //and be used to bundle together CRUD operations that have not originally been place in the same controller.
    //Check how our team prefers the organization of the project.
    @Operation(
        tags = {"Employee Api contoller"},
        summary = "Get operation to retrieve all stored Employees on the DB.",
        description = "Simple get method with no extra params required to display all currently stored Employees inside of the Database.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = false, description = "Leave request body empty."),
        responses = {
            @ApiResponse(responseCode = "200", description = "Response returned both when there are and there are not Employees saved in the Database.")
        }
        //externalDocs = @ExternalDocumentation(),
        

    )
    public List<Employee> hey() {
        return employeeServices.getAllEmployees();
    }

    @GetMapping("/{id}")
    @Operation(
        tags = {"Employee Api contoller"},
        summary = "Get one Employee from the Database, if it exists.",
        description = "Get one employee from the database by providing his/her id in as a path parameter. Example: writing 1 on the path will retrieve the Employee with id 1, if he/she exists in the Database.",
        parameters = {@Parameter(name = "id", example = "1", description = "Provide it as a number on the url path {id} where indicated")},
        responses = {
            @ApiResponse(responseCode = "200", description = "Response 200-ok whenever the provided id returns one found Employee from the Database.", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Response returned whenever the provided id does not find any Emoployee in the Database.")
        }

    )
    public ResponseEntity<Employee> getOneEmployee(@PathVariable("id") long id) {
        Optional<Employee> optionalEmployee = employeeServices.getOneEmployee(id);
        if(optionalEmployee.isPresent()) {
            return new ResponseEntity<Employee>(optionalEmployee.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    @Operation(
        tags = {"Employee Api contoller"}
    )
    public ResponseEntity<Employee> createNewEmployee(@RequestBody Employee employee) {
        employeeServices.createNewEmployee(employee);
        System.out.println(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
        tags = {"Employee Api contoller"}
    )
    public void deleteOneEmployee(@PathVariable("id") long id) {
        employeeServices.deleteOneEmployee(id);
    }

    @PutMapping("/update/{id}")
    @Operation(
        tags = {"Employee Api contoller"}
    )
    public ResponseEntity<Employee> updateOneEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
        Optional<Employee> optionalEmployee = employeeServices.getOneEmployee(id);

        if(optionalEmployee.isPresent()) {
            Employee foundEmployee = optionalEmployee.get();
            foundEmployee.setFirstName(employee.getFirstName());
            foundEmployee.setLastName(employee.getLastName());
            foundEmployee.setSalary(employee.getSalary());
            foundEmployee.setJobTitle(employee.getJobTitle());
            foundEmployee.setAge(employee.getAge());
            employeeRepository.save(foundEmployee);
            return new ResponseEntity<Employee>(foundEmployee, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
