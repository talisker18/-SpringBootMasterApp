package com.henz.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.henz.entity.department.Department;
import com.henz.error.DepartmentNotFoundException;
import com.henz.service.department.DepartmentService;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DepartmentService departmentService;
	
	private Department department;
	
	@BeforeEach
	void setup() {
		department = Department.builder()
				.departmentAddress("Zurich")
				.departmentCode("IT - 06")
				.departmentName("IT")
				.departmentId(1L)
				.build();
	}
	
	@Test
	void saveDepartment() throws Exception {
		Department inputDepartment = Department.builder()
				.departmentAddress("Zurich")
				.departmentCode("IT - 06")
				.departmentName("IT")
				.build();
		
		Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);
		
		mockMvc.perform(
				MockMvcRequestBuilders
					.post("/departments")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\r\n"
							+ "    \"departmentName\":\"IT\",\r\n"
							+ "    \"departmentAddress\":\"Zurich\",\r\n"
							+ "    \"departmentCode\":\"IT - 06\"\r\n"
							+ "}")
					
				).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void fetchDepartmentById() throws Exception {
		Mockito.when(departmentService.fetchDepartmentById(1L)).thenReturn(department);
		
		mockMvc.perform(
				MockMvcRequestBuilders
					.get("/departments/1")
					.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		//.andDo(MockMvcResultHandlers.print());
		.andExpect(MockMvcResultMatchers.jsonPath("$.departmentName").value(department.getDepartmentName()));
	}
}
