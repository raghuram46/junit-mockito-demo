package com.stg.service;

import java.util.List;

import com.stg.entity.Employee;


public interface EmployeeService {
	public abstract Employee saveEmployee(Employee employee);
	public abstract Employee getEmployeeById(Long id);
	public abstract List<Employee> getAllEmployees();
	public abstract Employee updateEmployee(Long id, Employee employee);
	public abstract String deleteEmployee(Long id);
}
