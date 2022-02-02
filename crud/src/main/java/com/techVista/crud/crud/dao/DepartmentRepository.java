package com.techVista.crud.crud.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techVista.crud.crud.entities.Department;
import com.techVista.crud.crud.entities.Employee;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	Optional<Department> findByDepartmentIdAndStatus(int departmentId, Boolean status);

	List<Department> findAllByStatus(Boolean status);

}
