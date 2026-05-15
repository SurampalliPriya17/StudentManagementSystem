package com.LaxmiPriya.StudentManagement.Dto;



import com.LaxmiPriya.StudentManagement.Entity.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 

public class UpdateResponseDto {


	private String message;
	private Student student;
}
