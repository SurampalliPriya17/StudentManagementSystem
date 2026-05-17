package com.LaxmiPriya.StudentManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.LaxmiPriya.StudentManagement.Dto.CreateRequestDto;
import com.LaxmiPriya.StudentManagement.Dto.ResponseDto;
import com.LaxmiPriya.StudentManagement.Dto.UpdateResponseDto;
import com.LaxmiPriya.StudentManagement.Entity.Course;
import com.LaxmiPriya.StudentManagement.Entity.Student;
import com.LaxmiPriya.StudentManagement.Exceptions.CourseNotFoundException;
import com.LaxmiPriya.StudentManagement.Exceptions.EmailAlreadyExistsException;
import com.LaxmiPriya.StudentManagement.Exceptions.StudentNotFoundException;
import com.LaxmiPriya.StudentManagement.Repo.CourseRepo;
import com.LaxmiPriya.StudentManagement.Repo.StudentLogRepo;
import com.LaxmiPriya.StudentManagement.Repo.StudentRepo;

@Service
@Transactional
public class StudentService {

	@Autowired
	StudentRepo studentRepo;

	@Autowired
	CourseRepo courseRepo;

	@Autowired
	StudentLogRepo studentLogRepo;

	@Autowired
	private AuditService auditService;

	public UpdateResponseDto addStudent(CreateRequestDto createDto)
			throws EmailAlreadyExistsException, CourseNotFoundException {

		studentRepo.findByEmail(createDto.getEmail()).ifPresent(student -> {
			throw new RuntimeException(new EmailAlreadyExistsException("Email is already existing!"));
		});
		Course course = courseRepo.findByCourseName(createDto.getCourseName())
				.orElseThrow(() -> new CourseNotFoundException("Course not found"));

		Student student = Student.builder().name(createDto.getName()).email(createDto.getEmail()).course(course)
				.DOB(createDto.getDOB()).build();

		studentRepo.save(student);

		auditService.saveLog();

		// for rollback case
		/*
		 * StudentLog log=new StudentLog();
		 * 
		 * log.setAction("Student Created"); log.setCreatedAt(LocalDateTime.now());
		 * 
		 * studentLogRepo.save(log);
		 */

		// int x = 10 / 0;

		return new UpdateResponseDto("Student admission is Successfull!", student);

	}

	public Student getStudent(Long id) throws StudentNotFoundException {

		return studentRepo.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found"));
	}

	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}

	public UpdateResponseDto updateStudent(Long id, @RequestBody CreateRequestDto createRequest)
			throws StudentNotFoundException, CourseNotFoundException {

		Student student = getStudent(id);
		Course course = courseRepo.findByCourseName(createRequest.getCourseName())
				.orElseThrow(() -> new CourseNotFoundException("Course not found"));

		student.setName(createRequest.getName());
		student.setCourse(course);
		student.setDOB(createRequest.getDOB());
		studentRepo.save(student);

		UpdateResponseDto updateResponse = new UpdateResponseDto();
		updateResponse.setStudent(student);
		updateResponse.setMessage("Student updated successfully");

		return updateResponse;

	}

	public ResponseDto deleteStudent(Long id) throws NotFoundException, StudentNotFoundException {

		Student student = getStudent(id);

		studentRepo.deleteById(id);

		return new ResponseDto("Successfully deleted!");
	}

	public List<Student> findByName(String name) {

		// List<Student> l= studentRepo.findByName(name);
		// return l;

		return studentRepo.findByName(name);
	}

	/*
	 * public List<Student> IgnoreCase(String prefix) { return
	 * studentRepo.findByFirstnameIgnoreCase(prefix); }
	 */

}
