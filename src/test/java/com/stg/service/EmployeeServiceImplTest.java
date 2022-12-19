package com.stg.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.ResourceAccessException;

import com.stg.entity.Employee;
import com.stg.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;

	private Employee employee;

	@BeforeEach
	void setUp() throws Exception {
		employee = Employee.builder().id(1L).firstName("Ramesh").lastName("Tendulkar").email("ramesh@gmail.com")
				.build();
	}

	@Test
	void testSaveEmployee_positive() {
		BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());

		BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);

		Employee savedEmployee = employeeServiceImpl.saveEmployee(employee);

		assertThat(savedEmployee).isNotNull();
	}

	@Test
	void testSaveEmployee_negative() {
		BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));

		assertThrows(ResourceAccessException.class, () -> employeeServiceImpl.saveEmployee(employee));

		verify(employeeRepository, never()).save(any(Employee.class));
	}

	@Test
	void testGetEmployeeById_positive() {
		BDDMockito.given(employeeRepository.findById(employee.getId())).willReturn(Optional.of(employee));

		Employee savedEmployee = employeeServiceImpl.getEmployeeById(employee.getId());

		assertThat(savedEmployee).isNotNull();
	}

	@Test
	void testGetEmployeeById_negative() {
		BDDMockito.given(employeeRepository.findById(employee.getId())).willReturn(Optional.empty());

		Exception exception = assertThrows(NullPointerException.class,
				() -> employeeServiceImpl.getEmployeeById(employee.getId()));

		System.out.println(exception.getMessage());
		verify(employeeRepository, times(1)).findById(any(long.class));
	}

	@Test
	void testGetAllEmployees_positive() {
		Employee employee1 = Employee.builder().id(2L).firstName("Tony").lastName("Stark").email("tony@gmail.com")
				.build();
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(employee);
		empList.add(employee1);

		BDDMockito.given(employeeRepository.findAll()).willReturn(empList);

		List<Employee> employees = employeeServiceImpl.getAllEmployees();

		assertThat(employees).isNotNull();
		assertThat(employees.size()).isEqualTo(2);
	}

	@Test
	void testGetAllEmployees_negative() {
		BDDMockito.given(employeeRepository.findAll()).willReturn(Collections.emptyList());

		List<Employee> employees = employeeServiceImpl.getAllEmployees();

		assertThat(employees).isEmpty();
		assertThat(employees.size()).isEqualTo(0);
	}

	@Test
	void testUpdateEmployee_positive() {
		Employee updatedEmployee = Employee.builder().firstName("Ram").lastName("Jadhav").email("ram@gmail.com")
				.build();

		BDDMockito.given(employeeRepository.findById(employee.getId())).willReturn(Optional.of(employee));
		BDDMockito.given(employeeRepository.save(updatedEmployee)).willReturn(updatedEmployee);

		Employee response = employeeServiceImpl.updateEmployee(employee.getId(), updatedEmployee);

		assertThat(response.getFirstName()).isEqualTo(updatedEmployee.getFirstName());
	}

	@Test
	void testUpdateEmployee_negative() {
		BDDMockito.given(employeeRepository.findById(employee.getId())).willReturn(Optional.empty());

		assertThrows(NullPointerException.class, () -> employeeServiceImpl.updateEmployee(employee.getId(), employee));

		verify(employeeRepository, never()).save(any(Employee.class));
	}

	@Test
	void testDeleteEmployee_positive() {
		BDDMockito.given(employeeRepository.findById(employee.getId())).willReturn(Optional.of(employee));
		BDDMockito.willDoNothing().given(employeeRepository).deleteById(employee.getId());

		String str = employeeServiceImpl.deleteEmployee(employee.getId());

		assertThat(str).isEqualTo("Employee Deleted");
		verify(employeeRepository, times(1)).deleteById(employee.getId());
	}

	@Test
	void testDeleteEmployee_negative() {
		BDDMockito.given(employeeRepository.findById(employee.getId())).willReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> employeeServiceImpl.deleteEmployee(employee.getId()));

		verify(employeeRepository, never()).deleteById(any(long.class));
	}

}
