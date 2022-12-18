package com.stg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.stg.entity.Employee;
import com.stg.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {
		Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
		if (savedEmployee.isPresent()) {
			throw new ResourceAccessException("Employee already exist with given email:" + employee.getEmail());
		}
		return employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(Long id) {
		Optional<Employee> empOptional = employeeRepository.findById(id);

		if (!empOptional.isPresent()) {
			throw new NullPointerException("Employee doesn't exist with the given Id: " + id);
		}
		return empOptional.get();
	}

	@Override
	public List<Employee> getAllEmployees() {

		return employeeRepository.findAll();
	}

	@Override
	public Employee updateEmployee(Long id, Employee employee) {
		Optional<Employee> empOptional = employeeRepository.findById(id);

		if (!empOptional.isPresent()) {
			throw new NullPointerException("Employee doesn't exist with the given Id: " + id);
		}
		return employeeRepository.save(employee);
	}

	@Override
	public String deleteEmployee(Long id) {
		Optional<Employee> empOptional = employeeRepository.findById(id);

		if (!empOptional.isPresent()) {
			throw new IllegalArgumentException("Employee doesn't exist with the given Id: " + id);
		}
		employeeRepository.deleteById(id);
		return "Employee Deleted";
	}

}
