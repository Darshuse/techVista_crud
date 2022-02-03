package com.techVista.crud.crud.controller;

import java.net.URI;
import java.util.List;
//import javax.validation.Valid;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.techVista.crud.crud.entities.Employee;
import com.techVista.crud.crud.exception.EmployeeNotFoundException;
import com.techVista.crud.crud.service.EmployeeService;
@CrossOrigin
@RestController
public class EmployeeController {
	@Autowired
	EmployeeService service;

	@GetMapping(path = "/employees")
	public List<Employee> findAll() {
		List<Employee> employeeList = service.findAll();
		if (employeeList == null || employeeList.isEmpty())
			throw new EmployeeNotFoundException("no employees found ");
		return employeeList;
	}


	
	@GetMapping(path = "/employees/id/{id}")
	public Employee find(@PathVariable int id) {
		Employee employee = service.find(id).orElse(null);

		if (employee == null)
			throw new EmployeeNotFoundException("no employees found ");
		return employee;
	}

	// @Valid
	@PostMapping(path = "/employees")
	public ResponseEntity<Employee> save(@RequestBody @Valid  Employee employee) {

		Employee savedEmployee = service.save(employee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id/{id}")
				.buildAndExpand(savedEmployee.getEmployeeId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/employees")
	public Optional<Employee> update(@RequestBody Employee employee) {
		Optional<Employee> updatedEmployee = service.update(employee);
		if (updatedEmployee == null)
			throw new EmployeeNotFoundException("no employees found ");
		return updatedEmployee;
	}

	@DeleteMapping("/employees/id/{id}")
	public void delete(@PathVariable int id) {

		service.delete(id);
	}
}
