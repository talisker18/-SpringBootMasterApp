package com.henz.entity.student_app;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//embedd this class into Student
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
//in student tbl, columns are named as guardian_name, guardian_email and guardian_mobile
//after adding embedable and changing the field names, we are missing the matching between field names and the column names
//therefore we have to override them
@AttributeOverrides({
	@AttributeOverride(
			name = "name",
			column = @Column(name = "guardian_name")
			),
	@AttributeOverride(
			name = "email",
			column = @Column(name = "guardian_email")
			),
	@AttributeOverride(
			name = "mobile",
			column = @Column(name = "guardian_mobile")
			)
})
public class Guardian {

	private String name;
	private String email;
	private String mobile;
}
