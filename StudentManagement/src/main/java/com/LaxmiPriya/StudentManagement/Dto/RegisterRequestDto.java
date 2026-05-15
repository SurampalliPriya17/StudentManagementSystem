package com.LaxmiPriya.StudentManagement.Dto;

import java.time.LocalDate;

import com.LaxmiPriya.StudentManagement.Entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor 
public class RegisterRequestDto {

	private String userName;
	private String email;
	private String password;
	private Role role;
}
