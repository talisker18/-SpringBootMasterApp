package com.henz.repository;

import java.util.List;

import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.henz.entity.student_app.Student;

import jakarta.transaction.Transactional;

//see also in web for query creation: spring jpa query creation

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	List<Student> findByFirstName(String firstName); //method name must match a certain pattern. Here it must match the name of the field, in this case 'firstName' 
	List<Student> findByFirstNameContaining(String name);
	List<Student> findByLastNameNotNull(); //get all records whose last_name is not null
	List<Student> findByGuardianName(String guardianName); //match the field name of Guardian's field 'name'
	Student findByFirstNameAndLastName(String firstName, String lastName); // AND
	
	//only gets 1 student because email is unique constraint
	@Query("select s from Student s where s.email = ?1") //JPQL style...?1 means first param
	Student getStudentByEmailAddress(String email);
	
	@Query("select s.firstName from Student s where s.email = ?1") //returns only 1 field (firstName)
	String getStudentFirstNameByEmailAddress(String email);
	
	//native query
	@Query(value = "select * from tbl_student s where s.email_address = ?1", nativeQuery = true) 
	Student getStudentByEmailAddressNative(String email);
	
	//native query with named param
	@Query(value = "select * from tbl_student s where s.email_address = :email", nativeQuery = true) 
	Student getStudentByEmailAddressNativeUsingNamedParam(@Param("email") String email);
	
	@Modifying(clearAutomatically = true)
	@Transactional //put the DB update in a single DB transaction. Meaning that after the method call, the update will be committed to DB
	@Query(value = "update tbl_student set first_name = ?1 where email_address = ?2", nativeQuery = true) 
	int updateStudentNameByEmail(String firstName, String email);
	
	//same as above, but use Student object
	
}
