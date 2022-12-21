package com.stg.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.stg.entity.Employee;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private TestEntityManager entityManager;

	private Employee employee;

	@BeforeEach
	public void setup() {
		employee = Employee.builder().firstName("Ramesh").lastName("Tendulkar").email("ramesh@gmail,com").build();
		entityManager.persist(employee);
	}

	@Test
	@Order(1)
	public void saveEmployee_success() {
		Employee savedEmployee = employeeRepository.save(employee);

		assertThat(savedEmployee).isNotNull();
		assertThat(savedEmployee.getId()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	void saveAll_success() {
		List<Employee> employees = new ArrayList<>();
		employees.add(
				Employee.builder().id(1L).firstName("Ramesh").lastName("Tendulkar").email("ramesh@gmail.com").build());
		employees.add(
				Employee.builder().id(2L).firstName("Sachin").lastName("Tendulkar").email("sachin@gmail.com").build());
		employees.add(
				Employee.builder().id(3L).firstName("Arjun").lastName("Tendulkar").email("arjun@gmail.com").build());

		List<Employee> empList = employeeRepository.saveAll(employees);

		assertThat(empList.size()).isGreaterThan(1);
	}

	@Test
	@Order(3)
	void findAll_success() {
		List<Employee> employees = employeeRepository.findAll();
		
		assertThat(employees.size()).isGreaterThanOrEqualTo(1);
	}

	@Test
	@Order(4)
	void findById_success() {
		employeeRepository.save(employee);
		
		Employee employeeDB = employeeRepository.findById(employee.getId()).get();
		
		assertThat(employeeDB).isNotNull();
	}

	@Test
	@Order(5)
	public void update_success() {
		employeeRepository.save(employee);

		Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
		savedEmployee.setEmail("ram@gmail.com");
		savedEmployee.setFirstName("Ram");
		Employee updatedEmployee = employeeRepository.save(savedEmployee);

		assertThat(updatedEmployee.getEmail()).isEqualTo("ram@gmail.com");
		assertThat(updatedEmployee.getFirstName()).isEqualTo("Ram");
	}

	@Test
	@Order(6)
	public void deleteById_success() {
		employeeRepository.save(employee);

		employeeRepository.deleteById(employee.getId());
		Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

		assertThat(employeeOptional).isEmpty();
	}

}
