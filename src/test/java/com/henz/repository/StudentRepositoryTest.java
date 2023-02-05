package com.henz.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.Modifying;

import com.henz.entity.student_app.Guardian;
import com.henz.entity.student_app.Student;

@DataJpaTest //data will be deleted in DB after test. Use @SpringBootTest if you want to store data permanently
public class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;
	
	private final Student s1 = Student.builder()
			.email("hjoel87@gmx.ch")
			.firstName("Joel")
			.lastName("Henz")
			.guardian(new Guardian("guard1", "guard1@gmx.ch", "123456"))
			//.guardianName("guard1")
			//.guardianEmail("guard1@gmx.ch")
			//.guardianMobile("123456")
			.build();
	
	private final Student s2 = Student.builder()
			.email("hjoel88@gmx.ch")
			.firstName("Joel2")
			.lastName("Henz")
			.guardian(new Guardian("guard12", "guard12@gmx.ch", "654321"))
			//.guardianName("guard12")
			//.guardianEmail("guard12@gmx.ch")
			//.guardianMobile("654321")
			.build();
	
	@Test
	public void saveStudent() {
		this.studentRepository.save(s1);
	}
	
	@Test
	public void printAllStudent() {
		this.studentRepository.save(s1);
		this.studentRepository.save(s2);
		
		final List<Student> list = this.studentRepository.findAll();
		
		System.out.println("printing all students: "+list);
	}
	
	@Test
	public void printStudentsByFirstname() {
		this.studentRepository.save(s1);
		this.studentRepository.save(s2);
		
		final List<Student> list = this.studentRepository.findByFirstName("Joel");
		
		System.out.println("printing all students: "+list);
	}
	
	@Test
	public void printStudentsByFirstnameContaining() {
		this.studentRepository.save(s1);
		this.studentRepository.save(s2);
		
		final List<Student> list = this.studentRepository.findByFirstNameContaining("Joel");
		
		System.out.println("printing all students: "+list);
	}
	
	@Test
	public void printStudentsBasedOnGuardianName() {
		this.studentRepository.save(s1);
		this.studentRepository.save(s2);
		
		final List<Student> list = this.studentRepository.findByGuardianName("guard12");
		
		System.out.println("printing all students: "+list);
	}
	
	@Test
	public void getStudentByEmail() {
		this.studentRepository.save(s1);
		this.studentRepository.save(s2);
		
		Student s = this.studentRepository.getStudentByEmailAddress("hjoel87@gmx.ch");
		
		System.out.println("printing student: "+s);
	}
	
	@Test
	public void getStudentNameByEmail() {
		this.studentRepository.save(s1);
		this.studentRepository.save(s2);
		
		String str = this.studentRepository.getStudentFirstNameByEmailAddress("hjoel87@gmx.ch");
		
		System.out.println("printing student name: "+str);
	}
	
	@Test
	public void updateStudentByEmail() {
		this.studentRepository.save(s1);
		this.studentRepository.save(s2);
		
		int updated = this.studentRepository.updateStudentNameByEmail("joelupdate", "hjoel87@gmx.ch");
		//this.studentRepository.flush();
		
		System.out.println(updated);
		
		Student s = this.studentRepository.getStudentByEmailAddress("hjoel87@gmx.ch");
		
		System.out.println("printing student name: "+s); //attention: this will NOT print the updated value (when using @DataJpaTest) unless we define clearAutomatically = true in the @Modifying
		//this will clear the underlying persistence context after the update. With false, the underlying persistence context still has name = "Joel" (old value)
		
	}
}
