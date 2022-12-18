package com.stg.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stg.entity.Employee;
import com.stg.service.EmployeeService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

	@Mock
	private EmployeeService employeeService;

	@InjectMocks
	private EmployeeController employeeController;

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setUp() {
		// MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}

	@Test
	@Order(1)
	void testCreateEmployee() throws Exception {

		// precondition or setup
		Employee employee = Employee.builder().id(1L).firstName("Ramesh").lastName("Tendulkar")
				.email("ramesh@gmail.com").build();

		when(employeeService.saveEmployee(employee)).thenReturn(employee);

		// Employee testedEmployee =
		// employeeController.createEmployee(employee).getBody();
		// assertThat(testedEmployee.getFirstName()).isEqualTo("Ramesh");

		// when - action or behavior that we are going test
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/employee")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(employee));

		// then - verify the result or output using assert statements
		mockMvc.perform(mockRequest).andDo(MockMvcResultHandlers.print()).andExpect(status().isCreated())
				.andExpect(jsonPath("$", notNullValue(null))).andExpect(jsonPath("$.firstName", is("Ramesh")));

	}

	@Test
	@Order(2)
	void testGetEmployeeById() throws Exception {
		Employee employee = Employee.builder().id(1L).firstName("Ramesh").lastName("Tendulkar")
				.email("ramesh@gmail.com").build();

		when(employeeService.getEmployeeById(1L)).thenReturn(employee);

		// Employee testedEmployee = employeeController.getEmployeeById(1L).getBody();
		// assertThat("Ramesh".equals(testedEmployee.getFirstName()));

		// when - action or behavior that we are going test ResultActions response =
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/employee/{id}", employee.getId())
				.contentType(MediaType.APPLICATION_JSON_VALUE));

		// then - verify the result or output using assert statements
		response.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue(null))).andExpect(jsonPath("$.firstName", is("Ramesh")));

	}

	@Test
	@Order(3)
	void testGetAllEmployees() throws Exception {
		List<Employee> employees = new ArrayList<>();
		employees.add(
				Employee.builder().id(1L).firstName("Ramesh").lastName("Tendulkar").email("ramesh@gmail.com").build());
		employees.add(
				Employee.builder().id(2L).firstName("Sachin").lastName("Tendulkar").email("sachin@gmail.com").build());
		employees.add(
				Employee.builder().id(3L).firstName("Arjun").lastName("Tendulkar").email("arjun@gmail.com").build());

		when(employeeService.getAllEmployees()).thenReturn(employees);

		// List<Employee> testedEmployees =
		// employeeController.getAllEmployees().getBody();
		// assertThat(testedEmployees.size()).isEqualTo(3);

		mockMvc.perform(MockMvcRequestBuilders.get("/employee").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(employees.size())))
				.andExpect(jsonPath("$[1].email", is("sachin@gmail.com")));

	}

	@Test
	@Order(4)
	void testUpdateEmployeePositive() throws Exception {
		long employeeId = 1L;

		Employee updatedEmployee = Employee.builder().id(1L).firstName("Ram").lastName("Jadhav").email("ram@gmail.com")
				.build();

		when(employeeService.updateEmployee(1L, updatedEmployee)).thenReturn(updatedEmployee);

		// Employee testedEmployee = employeeController.updateEmployee(employeeId,
		// updatedEmployee).getBody();
		// assertThat(testedEmployee.getFirstName()).isEqualTo("Ram");

		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/employee/{id}", employeeId)
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(updatedEmployee)));

		response.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.firstName", is(updatedEmployee.getFirstName())));
	}

	@Test
	@Order(5)
	void testDeleteEmployee() throws Exception {
		Long employeeId = 1L;

		when(employeeService.deleteEmployee(employeeId)).thenReturn("employee deleted");

		mockMvc.perform(MockMvcRequestBuilders.delete("/employee/{id}", employeeId))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}

}
