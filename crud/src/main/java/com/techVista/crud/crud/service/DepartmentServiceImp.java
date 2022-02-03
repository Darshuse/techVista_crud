package com.techVista.crud.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techVista.crud.crud.dao.DepartmentRepository;
import com.techVista.crud.crud.entities.Department;
import com.techVista.crud.crud.entities.Employee;
import com.techVista.crud.crud.exception.DepartmentForbiddenDeleteException;
import com.techVista.crud.crud.exception.DepartmentNotFoundException;

@Service
public class DepartmentServiceImp implements DepartmentService {

	@Autowired
	DepartmentRepository repo;
	@Autowired
	EmployeeServiceImp empService;

	@Override
	public List<Department> findAll() {
		// TODO FindAll method get all Department as List
		// implement soft delete
		List<Department> departmentList = repo.findAllByStatus(true);

		return departmentList;

	}

	@Override
	public Optional<Department> update(Department department) {
		// TODO Auto-generated method stub
		Optional<Department> updatedDepartment = find(department.getDepartmentId());
		if ((updatedDepartment.orElse(null)) != null) {

			updatedDepartment.get().setDepartmentId(department.getDepartmentId());
			updatedDepartment.get().setDepartmentName(department.getDepartmentName());
			updatedDepartment.get().setManagerId(department.getManagerId());
			updatedDepartment.get().setStatus(true);
			repo.save(updatedDepartment.get());
		}
		return updatedDepartment;
	}

	@Override
	public Optional<Department> find(int id) {
		// TODO find method in order to get Department by id and status as we implement
		// soft delete

		return repo.findByDepartmentIdAndStatus(id, true);
	}

	@Override
	public Department save(Department department) {
		// TODO save-department method stub
		department.setStatus(true);
		Department savedDepartment = repo.save(department);

		return savedDepartment;

	}

	@Override
	public void delete(Integer id) {
		// TODO delete-department method delete by departmentId(soft-delete)
		Optional<Department> deletedDepartment = find(id);
		if ((deletedDepartment.orElse(null)) == null)
			throw new DepartmentNotFoundException("no Department found ");
		List<Employee>departmentEmployees=empService.findByDepartmentId(id);

		if (departmentEmployees.size() >0 || !departmentEmployees.isEmpty())
			throw new DepartmentForbiddenDeleteException(" delete department is not allowed  as it not empty have number of employees");

		deletedDepartment.get().setStatus(false);
		repo.save(deletedDepartment.get());

	}

}
