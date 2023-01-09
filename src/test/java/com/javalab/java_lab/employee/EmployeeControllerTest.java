package com.javalab.java_lab.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.javalab.java_lab.department.Department;
import com.javalab.java_lab.department.DepartmentRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeServices employeeServices;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void testGetAllEmployees() {
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setFirstName("John");
        employee1.setLastName("Doe");
        employee1.setAge(30);
        employee1.setSalary(300.5);
        employee1.setJobTitle("something title");

        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setFirstName("Jane");
        employee1.setLastName("Doe");
        employee2.setAge(25);
        employee1.setSalary(300.5);
        employee1.setJobTitle("something title 2");

        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeServices.getAllEmployees()).thenReturn(employees);

        List<Employee> result = employeeController.getAllEmployees();
        assertEquals(employees, result);
    }

    @Test
    public void testGetOneEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setAge(30);
        employee.setSalary(300.5);
        employee.setJobTitle("something title");

        when(employeeServices.getOneEmployee(1L)).thenReturn(Optional.of(employee));

        ResponseEntity<?> result = employeeController.getOneEmployee(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(employee, result.getBody());

        when(employeeServices.getOneEmployee(2L)).thenReturn(Optional.empty());

        result = employeeController.getOneEmployee(2L);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(
            "{ \"errorCode\": \"404\", " +
            "  \"errorMessage\": \"No Employee was found with the given id.\", " +
            "  \"subCode\": \"Oracle error code if any or any other error\", " +
            "  \"details\": \"error description from oracle if any or other error\" }",
            result.getBody());
    }

    @Test
    public void testCreateOneEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setAge(30);
        employee.setSalary(300.5);
        employee.setJobTitle("something title");

        when(employeeServices.createNewEmployee(employee)).thenReturn(employee);

        ResponseEntity<Employee> result = employeeController.createNewEmployee(employee);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(employee, result.getBody());
    }

    @Test
    public void testDeleteOneEmployee(){
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setAge(30);
        employee.setSalary(300.5);
        employee.setJobTitle("something title");

        verify(employeeServices.deleteOneEmployee(1L));
    }

    @Test
    public void testUpdateOneEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setAge(30);
        employee.setSalary(300.5);
        employee.setJobTitle("something title");

        Department department = new Department();
        department.setId(1L);
        department.setName("Prestigious Comics");
        department.setDescription("Housing the best comics there are");
        department.setLocation("Locomotion");

        when(employeeServices.getOneEmployee(1)).thenReturn(Optional.of(employee));

        // Apparently the line below is unnecessary code.
        // when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Employee secondEmployee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Dave");
        employee.setLastName("Chapelle");
        employee.setAge(60);
        employee.setSalary(500.75);
        employee.setJobTitle("Cool Comedian");

        ResponseEntity<?> result = employeeController.updateOneEmployee(1L, secondEmployee);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(employee, result.getBody());

        result = employeeController.updateOneEmployee(2L, secondEmployee);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(null, result.getBody());
    }
}
