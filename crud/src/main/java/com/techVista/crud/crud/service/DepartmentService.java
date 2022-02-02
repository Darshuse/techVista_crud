package com.techVista.crud.crud.service;

import java.util.List;
import java.util.Optional;

import com.techVista.crud.crud.entities.Department;

public interface DepartmentService {

	public List<Department> findAll();

	public Optional<Department> update(Department department);

	public Optional<Department> find(int id);

	public Department save(Department department);

	public void delete(Integer id);

}
