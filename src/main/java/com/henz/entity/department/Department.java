package com.henz.entity.department;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * use hibernate validation (contained in spring-boot-starter-validation) to validate the input fields
 * 
 * */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long departmentId;
	@NotBlank(message = "Please provide a department name") //validate this also on controller level, see POST method. Here, invalid inputs are: whole property 'departmentName' is missing in request or there is a blank value, like "departmentName":""
	//more validations:
	/*@Length(max = 5, min = 1)
	@Size(max = 10, min = 0)
	@Email
	@Positive
	@Negative
	@Future
	@FutureOrPresent
	@Past
	@PastOrPresent*/
	private String departmentName;
	private String departmentAddress;
	private String departmentCode;
	
}
