package com.techVista.crud.crud.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techVista.crud.crud.dao.EmployeeRepository;
import com.techVista.crud.crud.entities.Employee;
import com.techVista.crud.crud.exception.EmployeeNotFoundException;

@Service
public class EmployeeServiceImp implements EmployeeService {
	@Autowired
	EmployeeRepository repo;

	@Override
	public List<Employee> findAll() {
		// TODO FindAll method get all Embloyees as List .

		List<Employee> employeeList = repo.findAllByStatus(true);

		return employeeList;
	}

	@Override
	public Optional<Employee> findByEmail(String email) {
		// TODO findByEmail method in order to get employee by email

		return repo.findByEmail(email);
	}

	@Override
	public Optional<Employee> find(int id) {
		// TODO findByEmail method in order to get employee by id and status as we
		// implement soft delete

		return repo.findByEmployeeIdAndStatus(id, true);
	}

	@Override
	public Optional<Employee> update(Employee employee) {
		// TODO update-employee method to update the employee object in database with
		// new updates
		Optional<Employee> updatedEmployee = find(employee.getEmployeeId());
		if ((updatedEmployee.orElse(null)) != null) {

			updatedEmployee.get().setDepartment_id(employee.getDepartment_id());
			updatedEmployee.get().setEmail(employee.getEmail());
			updatedEmployee.get().setEmployeeId(employee.getEmployeeId());
			updatedEmployee.get().setFirstName(employee.getFirstName());
			updatedEmployee.get().setHireDate(employee.getHireDate());
			updatedEmployee.get().setLastName(employee.getLastName());
			updatedEmployee.get().setManagerId(employee.getManagerId());
			updatedEmployee.get().setPhoneNumber(employee.getPhoneNumber());
			updatedEmployee.get().setSalary(employee.getSalary());
			updatedEmployee.get().setStatus(employee.getStatus());
			repo.save(updatedEmployee.get());
		}
		return updatedEmployee;
	}

	@Override
	public Employee save(Employee employee) {
		// TODO save-employee method stub
		employee.setStatus(true);
		Employee savedEmployee = repo.save(employee);

		return savedEmployee;
	}

	@Override
	public void delete(Integer id) {
		// TODO delete-employee method delete by employee_id(soft-delete)
		Optional<Employee> deletedEmployee = find(id);
		if ((deletedEmployee.orElse(null)) == null)
			throw new EmployeeNotFoundException("no employees found ");

		deletedEmployee.get().setStatus(false);
		repo.save(deletedEmployee.get());
	}

	@Override
	public List<Employee> findByDepartmentId(int departmentId) {
		// TODO Auto-generated method stub
		List<Employee> employeeListPerDepartmentId = findAll().stream()
				.filter(emp -> emp.getDepartment_id() == departmentId).collect(Collectors.toList());
		return employeeListPerDepartmentId;
	}

}
