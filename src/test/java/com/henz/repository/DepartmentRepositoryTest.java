package com.henz.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.henz.entity.department.Department;

@DataJpaTest
class DepartmentRepositoryTest {
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@BeforeEach
	void setup() {
		Department department = Department.builder()
				.departmentName("Mechanical Engineering")
				.departmentCode("ME - 011")
				.departmentAddress("Zurich")
				.build();
		
		entityManager.persist(department);
	}
	
	@Test
	public void whenFindById_thenReturnDepartment() {
		Department department = departmentRepository.findById(1L).get();
		Assertions.assertEquals(department.getDepartmentName(), "Mechanical Engineering");
	}
}
