// package com.javalab.java_lab.department;

// import static org.mockito.Mockito.times;

// import java.util.Arrays;
// import java.util.List;

// import org.junit.jupiter.api.Assertions;
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
// import com.javalab.java_lab.controller.DepartmentController;
// import com.javalab.java_lab.dao.DepartmentRepository;
// import com.javalab.java_lab.model.Department;
// import com.javalab.java_lab.model.Response;
// import com.javalab.java_lab.service.DepartmentServices;


// @ExtendWith(MockitoExtension.class)
// @DisplayName("DepartmentControllerTest")
// public class DepartmentControllerTest extends AppConfigTest {

//     @MockBean
//     private DepartmentRepository departmentRepository;

//     @MockBean
//     private DepartmentServices departmentServices;

//     @Autowired
//     private DepartmentController departmentController;

//     private Department createMockDepartment() {
//         Department department = Mockito.mock(Department.class);
//         department.setId(1L);
//         department.setName("Mock department");
//         department.setDescription("Description of Mock dept");
//         department.setLocation("Location of mock dept.");
//         return department;
//     }

//     private Department createMockDepartment2() {
//         Department department = Mockito.mock(Department.class);
//         department.setId(2L);
//         department.setName("Mock department 2 ");
//         department.setDescription("Description of Mock dept 2 ");
//         department.setLocation("Location of mock dept. 2");
//         return department;
//     }

//     @Test
//     public void testGetAllDepartments() {
//         Department department1 = createMockDepartment();
//         Department department2 = createMockDepartment2();
//         List<Department> departments = Arrays.asList(department1, department2);
//         Response response = new Response(true, "200 - All Departments retrieved", HttpStatus.OK, departments);
//         Mockito.when(departmentServices.retrieveAllDepartments()).thenReturn(response);

//         ResponseEntity<Response> result = departmentController.getAllDepartments();
//         Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
//         Assertions.assertEquals(HttpStatus.OK, result.getBody().getStatus());
//         Assertions.assertEquals(departments, result.getBody().getData());
//     }

//     @Test
//     @DisplayName("creates new dept successfully")
//     public void testCreateDepartment() {
//         Department department1 = createMockDepartment();

//         Response positiveResponse = new Response(true, "201 - Department Created", HttpStatus.CREATED, department1);
//         Mockito.when(departmentServices.createNewDeparment(department1)).thenReturn(positiveResponse);

//         ResponseEntity<Response> result = departmentController.createDepartment(department1);
//         Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
//         //trying to get rid of the null pointer exception
//         assert result.getBody() != null : "Response body is null";
//         Assertions.assertEquals(result.getBody().getData(), department1);
//         Mockito.verify(departmentServices).createNewDeparment(department1);
//     }

//     @Test
//     @DisplayName("creation fails, dept already exists")
//     public void testCreateDepartmentFails() {
//         Department department1 = createMockDepartment();

//         Response negativeResponse = new Response(false, "409 - Department already exists", HttpStatus.CONFLICT, null);
//         Mockito.when(departmentServices.createNewDeparment(department1)).thenReturn(negativeResponse);

//         ResponseEntity<Response> result = departmentController.createDepartment(department1);
//         Mockito.verify(departmentServices, times(1)).createNewDeparment(department1);
//         Assertions.assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
//         Assertions.assertEquals(result.getBody().getData(), null);
//     }

// }
