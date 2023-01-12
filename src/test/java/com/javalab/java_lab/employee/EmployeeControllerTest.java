// package com.javalab.java_lab.employee;

// import org.junit.jupiter.api.Assertions;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.when;

// import java.util.Arrays;
// import java.util.List;

// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mockito;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import com.javalab.java_lab.AppConfigTest;
// import com.javalab.java_lab.controller.EmployeeController;
// import com.javalab.java_lab.dao.DepartmentRepository;
// import com.javalab.java_lab.dao.EmployeeRepository;
// import com.javalab.java_lab.model.EmployeePojo;
// import com.javalab.java_lab.service.EmployeeServices;

// @ExtendWith(MockitoExtension.class)
// @DisplayName("EmployeeControllerTest")
// public class EmployeeControllerTest extends AppConfigTest {

//     @MockBean
//     private EmployeeServices employeeServices;

//     @MockBean
//     private DepartmentRepository departmentRepository;

//     @MockBean
//     private EmployeeRepository employeeRepository;

//     @Autowired
//     private EmployeeController employeeController;

//     private EmployeePojo createMockEmployee1() {
//         EmployeePojo employee = Mockito.mock(EmployeePojo.class);
//         employee.setId(1L);
//         employee.setFirstName("John");
//         employee.setLastName("Doe");
//         employee.setAge(30);
//         employee.setSalary(300.5);
//         employee.setJobTitle("something title");
//         employee.setDepartmentId(1L);
//         return employee;
//     }

//     private EmployeePojo createMockEmployee2() {
//         EmployeePojo employee = Mockito.mock(EmployeePojo.class);
//         employee.setId(2L);
//         employee.setFirstName("Jane");
//         employee.setLastName("Dae");
//         employee.setAge(25);
//         employee.setSalary(300.5);
//         employee.setJobTitle("Something Else Title");
//         return employee;
//     }

//     @Test
//     public void testGetAllEmployees() {
//         EmployeePojo employee1 = createMockEmployee1();
//         EmployeePojo employee2 = createMockEmployee2();

//         List<EmployeePojo> employees = Arrays.asList(employee1, employee2);

//         when(employeeServices.getAllEmployees()).thenReturn(employees);

//         List<EmployeePojo> result = employeeController.getAllEmployees();
//         assertEquals(employees, result);
//     }

//     @Test
//     public void testGetOneEmployee() {
//         EmployeePojo employee = createMockEmployee1();

//         Long testEmployeeId = 1L;
//         ResponseEntity<EmployeePojo> positivoResponse = new ResponseEntity<EmployeePojo>(employee, HttpStatus.OK);
//         Mockito.doReturn(positivoResponse).when(employeeServices.getOneEmployee(testEmployeeId));

//         ResponseEntity<?> result = employeeController.getOneEmployee(1L);
//         assertEquals(HttpStatus.OK, result.getStatusCode());
//         assertEquals(employee, result.getBody());


//         ResponseEntity<String> negativeResponse = new ResponseEntity<String>("{ \"errorCode\": \"404\", " +
//         "  \"errorMessage\": \"No Employee was found with the given id.\", " +
//         "  \"subCode\": \"Oracle error code if any or any other error\", " +
//         "  \"details\": \"error description from oracle if any or other error\" }", HttpStatus.NOT_FOUND);
//         Mockito.doReturn(negativeResponse).when(employeeServices.getOneEmployee(2L));

//         result = employeeController.getOneEmployee(2L);
//         assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
//         assertEquals(
//                 "{ \"errorCode\": \"404\", " +
//                         "  \"errorMessage\": \"No Employee was found with the given id.\", " +
//                         "  \"subCode\": \"Oracle error code if any or any other error\", " +
//                         "  \"details\": \"error description from oracle if any or other error\" }",
//                 result.getBody());
//     }

//     @Test
//     public void testCreateOneEmployee() {
//         EmployeePojo employee = createMockEmployee1();

//         when(employeeServices.createNewEmployee(employee))
//                 .thenReturn(new ResponseEntity<EmployeePojo>(employee, HttpStatus.CREATED));

//         ResponseEntity<?> result = employeeController.createNewEmployee(employee);
//         assertEquals(HttpStatus.CREATED, result.getStatusCode());
//         assertEquals(employee, result.getBody());
//     }

//     @Test
//     public void testDeleteOneEmployee_exists() {
//         long id = 1L;
//         // Mockito.when(employeeRepository.existsById(id)).thenReturn(true);
//         // Mockito.doNothing().when(employeeRepository).deleteById(id);

//         ResponseEntity<String> positiveResponse = new ResponseEntity<String>("Employee with id 1 has been deleted", HttpStatus.OK);
//         Mockito.doReturn(positiveResponse).when(employeeServices.deleteOneEmployee(id));

//         ResponseEntity<?> result = employeeController.deleteOneEmployee(id);

//         // verify(employeeRepository, times(1)).existsById(id);
//         // verify(employeeRepository, times(1)).deleteById(id);
//         assertEquals(HttpStatus.OK, result.getStatusCode());
//         assertEquals("Employee with id 1 has been deleted", result.getBody());
//     }

//     @Test
//     public void testDeleteOneEmployee_doestNotExist() {
//         long id = 1L;

//         ResponseEntity<String> negativeResponse = new ResponseEntity<String>("{ \"errorCode\": \"404\", " +
//         "  \"errorMessage\": \"No Employee was found with the given id.\", " +
//         "  \"subCode\": \"Oracle error code if any or any other error\", " +
//         "  \"details\": \"error description from oracle if any or other error\" }", HttpStatus.NOT_FOUND);
//         Mockito.doReturn(negativeResponse).when(employeeServices.deleteOneEmployee(id));
               

//         ResponseEntity<?> result = employeeController.deleteOneEmployee(id);
//         Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
//         Assertions.assertEquals("{ \"errorCode\": \"404\", " +
//         "  \"errorMessage\": \"No Employee was found with the given id.\", " +
//         "  \"subCode\": \"Oracle error code if any or any other error\", " +
//         "  \"details\": \"error description from oracle if any or other error\" }", result.getBody());
//     }

//     @Test
//     public void testUpdateOneEmployee_succeeds() {
//         EmployeePojo updateEmployee = createMockEmployee2();
//         long id = 1L;

//         ResponseEntity<EmployeePojo> positiveResponse = new ResponseEntity<EmployeePojo>(updateEmployee, HttpStatus.OK);
        
//         Mockito.doReturn(positiveResponse).when(employeeServices.updateOneEmployee(id, updateEmployee));
//         ResponseEntity<?> result = employeeController.updateOneEmployee(id, updateEmployee);

//         Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
//         Assertions.assertEquals(updateEmployee, result.getBody());
//         Mockito.verify(employeeServices, Mockito.times(1)).updateOneEmployee(id, updateEmployee);
    
//     }

//     @Test
//     public void testUpdateOneEmployee_fails () {
//         EmployeePojo updatesEmployee = createMockEmployee2();
//         long id = 3L;

//         ResponseEntity<String> negativeResponse = new ResponseEntity<String>("{ \"errorCode\": \"404\", " +
//         "  \"errorMessage\": \"No Employee was found with the given id.\", " +
//         "  \"subCode\": \"Oracle error code if any or any other error\", " +
//         "  \"details\": \"error description from oracle if any or other error\" }", HttpStatus.NOT_FOUND);
//         Mockito.doReturn(negativeResponse).when(employeeServices.updateOneEmployee(id, updatesEmployee));

//         ResponseEntity<?> result = employeeController.updateOneEmployee(id, updatesEmployee);

//         Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
//         Assertions.assertEquals("{ \"errorCode\": \"404\", " +
//         "  \"errorMessage\": \"No Employee was found with the given id.\", " +
//         "  \"subCode\": \"Oracle error code if any or any other error\", " +
//         "  \"details\": \"error description from oracle if any or other error\" }", result.getBody());
//         Mockito.verify(employeeServices, Mockito.times(1)).updateOneEmployee(id, updatesEmployee);
//     }

// }