package com.techVista.crud.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.techVista.crud.crud.entities.Employee;

public interface EmployeeService {

	public List<Employee> findAll();

	public List<Employee> findByDepartmentId(int departmentId);

	public Optional<Employee> findByEmail(String email);

	public Optional<Employee> update(Employee employee);

	public Employee save(Employee employee);

	public void delete(Integer id);

	public Optional<Employee> find(int id);

}
