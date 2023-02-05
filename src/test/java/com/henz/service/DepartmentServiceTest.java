package com.henz.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.henz.entity.department.Department;
import com.henz.repository.DepartmentRepository;
import com.henz.service.department.DepartmentService;

@SpringBootTest
class DepartmentServiceTest { //public not necessary because its same package
	
	@Autowired
	private DepartmentService departmentService;
	
	@MockBean
	private DepartmentRepository departmentRepository;
	
	@BeforeEach
	void setUp() {
		String departmentNameForTesting = "IT";
		
		Department department = Department.builder()
				.departmentName(departmentNameForTesting)
				.departmentAddress("some address")
				.departmentCode("IT-06")
				.departmentId(1L)
				.build();
		
		//for example if we call this.departmentService.fetchDepartmentByName(departmentName); -> inside the fetchDepartmentByName method 
		//we use departmentRepository.findByDepartmentNameIgnoreCase method. Whenever this method is called, the mocked DepartmentRepository will return 'department'
		Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase(departmentNameForTesting)).thenReturn(department);
	}
	
	@Test
	@DisplayName("Get data based on valid department name")
	//@Disabled
	public void whenValidDepartmentName_thenDepartmentShouldBeFound() {
		String departmentName = "IT";
		Department found = this.departmentService.fetchDepartmentByName(departmentName);
		Assertions.assertEquals(departmentName, found.getDepartmentName());
	}

}
