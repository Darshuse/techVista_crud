package com.techVista.crud.crud.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techVista.crud.crud.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Optional<Employee> findByEmail(String email);

	Optional<Employee> findByEmployeeIdAndStatus(int employeeId, Boolean status);

	List<Employee> findAllByStatus(Boolean status);

}
