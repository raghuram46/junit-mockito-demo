package com.stg.service;

import java.util.List;

import com.stg.entity.Employee;


public interface EmployeeService {
	public abstract Employee saveEmployee(Employee employee);
	public abstract Employee geEemployeeById(Long id);
	public abstract List<Employee> getAllEmployees();
	public abstract Employee updatEmployee(Long id, Employee employee);
	public abstract List<Employee> deleteEmployee(Long id);
}
