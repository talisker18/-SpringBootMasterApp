package com.henz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.henz.entity.department.Department;

/*
 * 
 * important note: if you cant define a method by the naming convention, use @Query and write the JPQL 
 * 
 * or use native SQL query by using @Query("query...", nativeQuery = true)
 * 
 * */

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
	//JpaRepository will implement this method. But we have to follow a certain naming convention, like 
	// 'findBy' and then the exact field name of Department.class, in this case 'departmentName' --> but with camelCase
	
	//see also: appendix of https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#appendix --> these methods will create a JPQL behind the method
	public Department findByDepartmentName(String departmentName);
	
	//same as above, but ignoring case. naming convenction: 'IgnoreCase' at the end (?)
	public Department findByDepartmentNameIgnoreCase(String departmentName);
}
