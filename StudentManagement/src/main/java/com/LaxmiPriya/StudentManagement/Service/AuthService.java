package com.LaxmiPriya.StudentManagement.Service;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.LaxmiPriya.StudentManagement.Dto.LoginRequestDto;
import com.LaxmiPriya.StudentManagement.Dto.RegisterRequestDto;
import com.LaxmiPriya.StudentManagement.Dto.UpdateResponseDto;
import com.LaxmiPriya.StudentManagement.Entity.Course;
import com.LaxmiPriya.StudentManagement.Entity.Student;
import com.LaxmiPriya.StudentManagement.Entity.User;
import com.LaxmiPriya.StudentManagement.Exceptions.CourseNotFoundException;
import com.LaxmiPriya.StudentManagement.Exceptions.EmailAlreadyExistsException;
import com.LaxmiPriya.StudentManagement.Exceptions.UserNotFoundException;
import com.LaxmiPriya.StudentManagement.Repo.UserRepo;

@Service
public class AuthService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepo userRepo;

	

	public String register(RegisterRequestDto registerRequestDto) {

		userRepo.findByEmail(registerRequestDto.getEmail()).ifPresent(user -> {
			throw new EmailAlreadyExistsException("Email is already existing!");
		});

		User user = User.builder().userName(registerRequestDto.getUserName()).email(registerRequestDto.getEmail())
				.password(passwordEncoder.encode(registerRequestDto.getPassword())).role(registerRequestDto.getRole())
				.build();

		userRepo.save(user);

		return "Registration is Successful!";
	}

	public String login(LoginRequestDto loginRequestDto) throws UserNotFoundException {

		User user = userRepo.findByEmail(loginRequestDto.getEmail())
				.orElseThrow(() -> new UserNotFoundException("User not found!"));

		boolean isValid = passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword());

		if (!isValid) {
			throw new RuntimeException("Invalid Password");
		}
		return "login is successful!";

	}

}
