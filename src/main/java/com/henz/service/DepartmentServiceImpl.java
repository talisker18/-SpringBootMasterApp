package com.henz.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henz.entity.Department;
import com.henz.error.DepartmentNotFoundException;
import com.henz.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public List<Department> fetchAllDepartments() {
		return departmentRepository.findAll();
	}

	@Override
	public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
		Optional<Department> department = departmentRepository.findById(departmentId);
		
		if(!department.isPresent()) {
			throw new DepartmentNotFoundException("Department wit id: "+departmentId + " not available");
		}
		
		return department.get();
	}

	@Override
	public void deleteDepartmentById(Long departmentId) {
		departmentRepository.deleteById(departmentId);
	}

	@Override
	public Department updateDepartment(Long departmentId, Department department) throws DepartmentNotFoundException {
		//get current department in DB
		final Department current = this.fetchDepartmentById(departmentId);
		
		if(Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())) {
			current.setDepartmentName(department.getDepartmentName());
		}
		
		if(Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())) {
			current.setDepartmentAddress(department.getDepartmentAddress());
		}
		
		if(Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())) {
			current.setDepartmentCode(department.getDepartmentCode());
		}
		
		return departmentRepository.save(current);
	}

	//for this, no default method in JpaRepository is available. So go to DepartmentRepository and create one
	@Override
	public Department fetchDepartmentByName(String departmentName) {
		//return departmentRepository.find(departmentName); --> case sensitive
		return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
	}

}
