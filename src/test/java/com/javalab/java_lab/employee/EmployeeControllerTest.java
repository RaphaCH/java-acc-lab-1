package com.javalab.java_lab.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
import com.javalab.java_lab.department.Department;
import com.javalab.java_lab.department.DepartmentRepository;

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

    @Test
    public void shouldWork() {
        System.out.println("OK");
    }

    private Employee createMockEmployee1() {
        Employee employee = Mockito.mock(Employee.class);
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setAge(30);
        employee.setSalary(300.5);
        employee.setJobTitle("something title");
        employee.setDepartmentId(1L);
        return employee;
    }

    private Employee createMockEmployee2() {
        Employee employee = Mockito.mock(Employee.class);
        employee.setId(2L);
        employee.setFirstName("Jane");
        employee.setLastName("Dae");
        employee.setAge(25);
        employee.setSalary(300.5);
        employee.setJobTitle("Something Else Title");
        return employee;
    }

    private Department createMockDepartment() {
        Department department = Mockito.mock(Department.class);
        department.setId(1L);
        department.setName("Prestigious Comics");
        department.setDescription("Housing the best comics there are");
        department.setLocation("Locomotion");
        return department;
    }

    @Test
    public void testGetAllEmployees() {
        Employee employee1 = createMockEmployee1();
        Employee employee2 = createMockEmployee2();

        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeServices.getAllEmployees()).thenReturn(employees);

        List<Employee> result = employeeController.getAllEmployees();
        assertEquals(employees, result);
    }

    @Test
    public void testGetOneEmployee() {
        Employee employee = createMockEmployee1();

        Long testEmployeeId = 1L;
        when(employeeServices.getOneEmployee(testEmployeeId)).thenReturn(new ResponseEntity(employee, HttpStatus.OK));

        ResponseEntity<?> result = employeeController.getOneEmployee(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(employee, result.getBody());

        when(employeeServices.getOneEmployee(2L)).thenReturn(new ResponseEntity("{ \"errorCode\": \"404\", " +
                "  \"errorMessage\": \"No Employee was found with the given id.\", " +
                "  \"subCode\": \"Oracle error code if any or any other error\", " +
                "  \"details\": \"error description from oracle if any or other error\" }", HttpStatus.NOT_FOUND));

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
        Employee employee = createMockEmployee1();

        when(employeeServices.createNewEmployee(employee))
                .thenReturn(new ResponseEntity<Employee>(employee, HttpStatus.CREATED));

        ResponseEntity<?> result = employeeController.createNewEmployee(employee);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(employee, result.getBody());
    }

    // Can't get deleteTestMethod to work because of conditional method returning
    // void;

    // @Test
    // public void testDeleteOneEmployee_exists() {
    // long id = 1;
    // when(employeeRepository.existsById(id)).thenReturn(true);
    // doAnswer(new Answer<Void>() {
    // @Override public Void answer(InvocationOnMock invocation) {
    // return new ResponseEntity<String>("Employee with id 1 has been deleted",
    // HttpStatus.ok);
    // }
    // }).when(employeeRepository.deleteById(id));
    // ResponseEntity<String> result = employeeController.deleteOneEmployee(id);

    // verify(employeeRepository, times(1)).existsById(id);
    // verify(employeeRepository, times(1)).deleteById(id);
    // assertEquals(HttpStatus.OK, result.getStatusCode());
    // assertEquals("Employee with id 1 has been deleted", result.getBody());
    // }

    // @Test
    // public void testUpdateOneEmployee() {
    // Employee employee = createMockEmployee1();
    // Department department = createMockDepartment();
    // Employee secondEmployee = createMockEmployee2();
    // long mockId = 1L;

    // when(employeeRepository.findById(mockId)).thenReturn(Optional.of(employee));
    // // Apparently the line below is unnecessary code. why?
    // //
    // when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
    // doNothing().when(employeeRepository).save(employee);
    // doReturn(new ResponseEntity<Employee>(secondEmployee,
    // HttpStatus.OK)).when(employeeServices.updateOneEmployee(mockId,
    // secondEmployee));

    // ResponseEntity<?> result = employeeController.updateOneEmployee(1L,
    // secondEmployee);
    // assertEquals(HttpStatus.OK, result.getStatusCode());
    // assertEquals(employee, result.getBody());

    // result = employeeController.updateOneEmployee(2L, secondEmployee);
    // assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    // assertEquals(null, result.getBody());
    // }

    // @Test
    // public void testUpdateOneEmployee() {
    //     Employee employee = createMockEmployee1();
    //     Employee updateEmployee = createMockEmployee2();
    //     Department department = createMockDepartment();

    //     when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
    //     when(employeeRepository.save(employee)).thenReturn(employee);
    //     when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
    //     when(employeeServices.updateOneEmployee(1L, updateEmployee))
    //             .thenReturn(new ResponseEntity(employee, HttpStatus.OK));

    //     ResponseEntity<?> response = employeeServices.updateOneEmployee(1L, updateEmployee);
    //     System.out.println(response);
    //     assertEquals(HttpStatus.OK, response.getStatusCode());
    //     Employee updatedEmployee = (Employee) response.getBody();
    //     assertEquals("Jane", updatedEmployee.getFirstName());
    //     assertEquals("Dae", updatedEmployee.getLastName());
    //     assertEquals(300.5, updatedEmployee.getSalary(), 0);
    //     assertEquals("Something Else Title", updatedEmployee.getJobTitle());
    //     assertEquals(25, updatedEmployee.getAge().intValue());
    //     verify(employeeRepository, times(1)).save(updatedEmployee);
    // }
}