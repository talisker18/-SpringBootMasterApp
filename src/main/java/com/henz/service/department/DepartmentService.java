package com.henz.service.department;

import java.util.List;

import com.henz.entity.department.Department;
import com.henz.error.DepartmentNotFoundException;

public interface DepartmentService {

	public Department saveDepartment(Department department);

	public List<Department> fetchAllDepartments();

	public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

	public void deleteDepartmentById(Long departmentId);

	public Department updateDepartment(Long departmentId, Department department) throws DepartmentNotFoundException;

	public Department fetchDepartmentByName(String departmentName);

}
