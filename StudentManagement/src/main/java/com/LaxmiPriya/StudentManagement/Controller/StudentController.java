package com.LaxmiPriya.StudentManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LaxmiPriya.StudentManagement.Dto.CreateRequestDto;

import com.LaxmiPriya.StudentManagement.Dto.ResponseDto;
import com.LaxmiPriya.StudentManagement.Dto.UpdateResponseDto;
import com.LaxmiPriya.StudentManagement.Entity.Student;
import com.LaxmiPriya.StudentManagement.Exceptions.CourseNotFoundException;
import com.LaxmiPriya.StudentManagement.Exceptions.EmailAlreadyExistsException;
import com.LaxmiPriya.StudentManagement.Exceptions.InvalidStudentDataException;
import com.LaxmiPriya.StudentManagement.Exceptions.StudentNotFoundException;
import com.LaxmiPriya.StudentManagement.Service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")

public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping("/Admission")
	public ResponseEntity<UpdateResponseDto> addStudent(@Valid @RequestBody CreateRequestDto createDto)
			throws EmailAlreadyExistsException,CourseNotFoundException {

		return ResponseEntity.ok(studentService.addStudent(createDto));
	}

	@GetMapping("/getStudent/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable Long id)
			throws NotFoundException, StudentNotFoundException {
		return ResponseEntity.ok(studentService.getStudent(id));
	}

	@GetMapping("/getAllStudents")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> l = studentService.getAllStudents();
		return ResponseEntity.ok(l);
	}

	@PutMapping("/UpdateStudent/{id}")
	public ResponseEntity<UpdateResponseDto> updateStudent(@PathVariable Long id,
			@Valid @RequestBody CreateRequestDto createRequest) throws StudentNotFoundException, CourseNotFoundException {
		return ResponseEntity.ok(studentService.updateStudent(id, createRequest));
	}

	@DeleteMapping("/DeleteStudent/{id}")
	public ResponseEntity<ResponseDto> deleteStudent(@PathVariable Long id)
			throws NotFoundException, StudentNotFoundException {
		return ResponseEntity.ok(studentService.deleteStudent(id));
	}

	@GetMapping("/findByName/{name}")
	public ResponseEntity<List<Student>> findByName(@PathVariable String name) {
		List<Student> l = studentService.findByName(name);
		return ResponseEntity.ok(l);
	}

}