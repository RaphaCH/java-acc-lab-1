// package com.javalab.java_lab.department;

// import static org.junit.jupiter.api.Assertions.fail;
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

// import com.javalab.java_lab.AppConfigTest;
// import com.javalab.java_lab.controller.DepartmentController;
// import com.javalab.java_lab.dao.DepartmentRepository;
// import com.javalab.java_lab.model.DepartmentDto;
// import com.javalab.java_lab.service.DepartmentServices;

// import jakarta.persistence.EntityExistsException;

// @ExtendWith(MockitoExtension.class)
// @DisplayName("DepartmentControllerTest")
// public class DepartmentControllerTest extends AppConfigTest {

//     @MockBean
//     private DepartmentRepository departmentRepository;

//     @MockBean
//     private DepartmentServices departmentServices;

//     @Autowired
//     private DepartmentController departmentController;

//     private DepartmentDto createMockDepartment() {
//         DepartmentDto department = Mockito.mock(DepartmentDto.class);
//         department.setId(1L);
//         department.setName("Mock department");
//         department.setDescription("Description of Mock dept");
//         department.setLocation("Location of mock dept.");
//         return department;
//     }

//     private DepartmentDto createMockDepartment2() {
//         DepartmentDto department = Mockito.mock(DepartmentDto.class);
//         department.setId(2L);
//         department.setName("Mock department 2 ");
//         department.setDescription("Description of Mock dept 2 ");
//         department.setLocation("Location of mock dept. 2");
//         return department;
//     }

//     @Test
//     public void testGetAllDepartments() {
//         DepartmentDto department1 = createMockDepartment();
//         DepartmentDto department2 = createMockDepartment2();
//         List<DepartmentDto> departments = Arrays.asList(department1, department2);

//         Mockito.when(departmentServices.retrieveAllDepartments()).thenReturn(departments);

//         List<DepartmentDto> result = departmentController.getAllDepartments();
//         Assertions.assertEquals(departments, result);
//     }

//     @Test
//     @DisplayName("creates new dept successfully")
//     public void testCreateDepartment() {
//         DepartmentDto department1 = createMockDepartment();

//         Mockito.doNothing().when(departmentServices).createNewDeparment(department1);

//         DepartmentDto department = departmentController.createDepartment(department1);
//         Assertions.assertEquals(department, department1);
//         Mockito.verify(departmentServices).createNewDeparment(department);
//     }

//     @Test
//     @DisplayName("creation fails, dept already exists")
//     public void testCreateDepartmentFails() {
//         DepartmentDto department1 = createMockDepartment();
//         String dptName = "Mock department";

//         Mockito.doThrow(new EntityExistsException()).when(departmentServices).createNewDeparment(department1);

//         try {
//             DepartmentDto department = departmentController.createDepartment(department1);
//             fail("Expect EntityExistsException to be thrown");
//         } catch (EntityExistsException e) {
//             Mockito.verify(departmentServices, times(1)).createNewDeparment(department1);
//             Assertions.assertThrows(EntityExistsException.class, () -> {
//                 departmentServices.createNewDeparment(department1);
//             }, "department " + dptName + " already exists in the database");
//         }
//     }

    



    
// }
