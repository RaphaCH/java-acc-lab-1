package com.javalab.java_lab.employee;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.javalab.java_lab.AppConfigTest;
import com.javalab.java_lab.controller.EmployeeController;
import com.javalab.java_lab.dao.DepartmentRepository;
import com.javalab.java_lab.dao.EmployeeRepository;
import com.javalab.java_lab.model.EmployeeDto;
import com.javalab.java_lab.model.Response;
import com.javalab.java_lab.service.EmployeeServices;

@ExtendWith(MockitoExtension.class)
@DisplayName("EmployeeControllerTest")
public class EmployeeControllerTest extends AppConfigTest {

    @MockBean
    private EmployeeServices employeeServices;

    @MockBean
    private DepartmentRepository departmentRepository;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeController employeeController;

    private EmployeeDto createMockEmployee1() {
        EmployeeDto employee = Mockito.mock(EmployeeDto.class);
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setAge(30);
        employee.setSalary(300.5);
        employee.setJobTitle("something title");
        return employee;
    }

    private EmployeeDto createMockEmployee2() {
        EmployeeDto employee = Mockito.mock(EmployeeDto.class);
        employee.setId(2L);
        employee.setFirstName("Jane");
        employee.setLastName("Dae");
        employee.setAge(25);
        employee.setSalary(300.5);
        employee.setJobTitle("Something Else Title");
        return employee;
    }

    @Test
    public void testGetAllEmployees() {
        EmployeeDto employee1 = createMockEmployee1();
        EmployeeDto employee2 = createMockEmployee2();

        List<EmployeeDto> employees = Arrays.asList(employee1, employee2);
        Response response = new Response(true, "200 - All Employees retrieved", HttpStatus.OK, employees);

        when(employeeServices.getAllEmployees()).thenReturn(response);

        ResponseEntity<Response> result = employeeController.getAllEmployees();
        assertEquals(response.getStatus(), result.getStatusCode());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response.getData(), result.getBody().getData());
    }

    @Test
    public void testGetOneEmployee() {
        EmployeeDto employee = createMockEmployee1();

        Long testEmployeeId = 1L;
        Response positiveresponse = new Response(true, "Success", HttpStatus.OK, employee);
        when(employeeServices.getOneEmployee(testEmployeeId)).thenReturn(positiveresponse);


        ResponseEntity<Response> result = employeeController.getOneEmployee(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(HttpStatus.OK, positiveresponse.getStatus());
        assertEquals(employee, result.getBody().getData());


        Response negativeResponse = new Response(false, "404 - No Employee was found with the given id.", HttpStatus.NOT_FOUND, null);
        when(employeeServices.getOneEmployee(2L)).thenReturn(negativeResponse);

        result = employeeController.getOneEmployee(2L);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(
            "404 - No Employee was found with the given id.",
                result.getBody().getMessage());
    }

    @Test
    public void testCreateOneEmployee() {
        EmployeeDto employee = createMockEmployee1();

        Response response = new Response(true, "201 - Employee created", HttpStatus.CREATED, employee);
        when(employeeServices.createNewEmployee(employee)).thenReturn(response);

        ResponseEntity<Response> result = employeeController.createNewEmployee(employee);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(HttpStatus.CREATED, response.getStatus());
        assertEquals(employee, result.getBody().getData());
    }

    @Test
    public void testDeleteOneEmployee_exists() {
        long id = 1L;

        Response positiveResponse = new Response(true, "200 - Employee with id 1 has been deleted", HttpStatus.OK, null);
        Mockito.when(employeeServices.deleteOneEmployee(id)).thenReturn(positiveResponse);
        ResponseEntity<Response> result = employeeController.deleteOneEmployee(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(HttpStatus.OK, positiveResponse.getStatus());
        assertEquals("200 - Employee with id 1 has been deleted", result.getBody().getMessage());
    }

    @Test
    public void testDeleteOneEmployee_doestNotExist() {
        long id = 1L;

        Response negativeResponse = new Response(false, "404 - No Employee was found with the given id.", HttpStatus.NOT_FOUND, null);
        Mockito.when(employeeServices.deleteOneEmployee(id)).thenReturn(negativeResponse);   

        ResponseEntity<Response> result = employeeController.deleteOneEmployee(id);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, negativeResponse.getStatus());
        Assertions.assertEquals("404 - No Employee was found with the given id.", result.getBody().getMessage());
    }

    @Test
    public void testUpdateOneEmployee_succeeds() {
        EmployeeDto updateEmployee = createMockEmployee2();
        long id = 1L;

        Response positiveResponse = new Response(true, "Employee updated successfully", HttpStatus.OK, updateEmployee);
        
        Mockito.when(employeeServices.updateOneEmployee(id, null, updateEmployee)).thenReturn(positiveResponse);
        ResponseEntity<Response> result = employeeController.updateOneEmployee(id,null, updateEmployee);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, positiveResponse.getStatus());
        Assertions.assertEquals(updateEmployee, result.getBody().getData());
        Mockito.verify(employeeServices, Mockito.times(1)).updateOneEmployee(id,null, updateEmployee);
    
    }

    @Test
    public void testUpdateOneEmployee_fails () {
        EmployeeDto updatesEmployee = createMockEmployee2();
        long id = 3L;

        Response negativeResponse = new Response(false, "404 - Employee not found", HttpStatus.NOT_FOUND, null);
        Mockito.when(employeeServices.updateOneEmployee(id, null, updatesEmployee)).thenReturn(negativeResponse);
        ResponseEntity<Response> result = employeeController.updateOneEmployee(id, null, updatesEmployee);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        Assertions.assertEquals("404 - Employee not found", result.getBody().getMessage());
        Mockito.verify(employeeServices, Mockito.times(1)).updateOneEmployee(id, null, updatesEmployee);
    }

}