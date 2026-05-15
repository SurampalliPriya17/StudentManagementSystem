package com.LaxmiPriya.StudentManagement.Dto;

import java.time.LocalDate;

import com.LaxmiPriya.StudentManagement.Entity.Course;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor 
public class CreateRequestDto {
	
	
	
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@Email(message = "Invalid email")
	@Column(unique = true)
	private String email;
	
	@NotBlank(message = " Course is required")
	private String courseName;
	
	
	private LocalDate DOB;

	
	
}
