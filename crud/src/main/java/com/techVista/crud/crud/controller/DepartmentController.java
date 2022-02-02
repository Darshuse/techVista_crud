package com.techVista.crud.crud.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.techVista.crud.crud.entities.Department;
import com.techVista.crud.crud.exception.DepartmentNotFoundException;
import com.techVista.crud.crud.service.DepartmentServiceImp;

@RestController
public class DepartmentController {

	@Autowired
	DepartmentServiceImp service;
	
	@Autowired
	EmployeeController empController;

	@GetMapping(path = "/departments")
	public List<Department> findAll() {
		List<Department> departmentList = service.findAll();
		if (departmentList == null || departmentList.isEmpty())
			throw new DepartmentNotFoundException("no departments found ");
		return departmentList;
	}

	@GetMapping(path = "/departments/id/{id}")
	public Department find(@PathVariable int id) {
		Department department = service.find(id).orElse(null);

		if (department == null)
			throw new DepartmentNotFoundException("no departments found ");
		return department;
	}

	@PostMapping(path = "/departments")
	public ResponseEntity<Department> save(@RequestBody @Valid Department department) {

		Department savedDepartment = service.save(department);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id/{id}")
				.buildAndExpand(savedDepartment.getDepartmentId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/departments")
	public Optional<Department> update(@RequestBody Department department) {
		Optional<Department> updatedDepartment = service.update(department);
		if (updatedDepartment == null)
			throw new DepartmentNotFoundException("no employees found ");
		return updatedDepartment;
	}

	@DeleteMapping("/departments/id/{id}")
	public void delete(@PathVariable int id) {
		
		service.delete(id);
	}
}
