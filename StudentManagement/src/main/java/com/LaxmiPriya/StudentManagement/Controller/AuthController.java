package com.LaxmiPriya.StudentManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.LaxmiPriya.StudentManagement.Dto.LoginRequestDto;
import com.LaxmiPriya.StudentManagement.Dto.RegisterRequestDto;
import com.LaxmiPriya.StudentManagement.Exceptions.EmailAlreadyExistsException;
import com.LaxmiPriya.StudentManagement.Exceptions.UserNotFoundException;
import com.LaxmiPriya.StudentManagement.Service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequestDto registerRequestDto) throws EmailAlreadyExistsException {
      return ResponseEntity.ok(authService.register(registerRequestDto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) throws EmailAlreadyExistsException, UserNotFoundException {
      return ResponseEntity.ok(authService.login(loginRequestDto));
	}
}
