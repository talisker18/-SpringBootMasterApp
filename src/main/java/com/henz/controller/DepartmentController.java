package com.henz.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.henz.entity.Department;
import com.henz.error.DepartmentNotFoundException;
import com.henz.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	private final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DepartmentController.class);
	
	//@Valid: it will validate if the requestBody corresponds to the constraints defined in Department.class
	@PostMapping("/departments")
	public Department saveDepartment(@Valid @RequestBody Department department) { //with this annotation, JSON will be mapped to our java department object
		LOGGER.info("Insie of saveDepartment of POST Controller");
		return departmentService.saveDepartment(department);
	}
	
	@GetMapping("/departments")
	public List<Department> fetchAllDepartments(){
		LOGGER.info("Insie of fetchAllDepartments of GET Controller");
		return departmentService.fetchAllDepartments();
	}
	
	@GetMapping("/departments/{departmentId}")
	public Department fetchDepartmentById(@PathVariable("departmentId") Long departmentId) throws DepartmentNotFoundException{
		return departmentService.fetchDepartmentById(departmentId);
	}
	
	@DeleteMapping("/departments/{departmentId}")
	public String deleteDepartmentById(@PathVariable("departmentId") Long departmentId) {
		departmentService.deleteDepartmentById(departmentId);
		return "Department deleted successfully";
	}
	
	@PutMapping("/departments/{departmentId}")
	public Department updateDepartment(
			@PathVariable("departmentId") Long departmentId, 
			@RequestBody Department department
			) throws DepartmentNotFoundException {
		return departmentService.updateDepartment(departmentId, department);
	}
	
	@GetMapping("/departments/name/{name}")
	public Department fetchDepartmentByName(@PathVariable("name") String departmentName) {
		return departmentService.fetchDepartmentByName(departmentName);
	}
}
