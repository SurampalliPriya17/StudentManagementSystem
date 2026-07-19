package com.LaxmiPriya.StudentManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.LaxmiPriya.StudentManagement.Dto.CreateRequestDto;
import com.LaxmiPriya.StudentManagement.Dto.UpdateResponseDto;
import com.LaxmiPriya.StudentManagement.Dto.UpdateRequestDto;
import com.LaxmiPriya.StudentManagement.Entity.Student;
import com.LaxmiPriya.StudentManagement.Exceptions.StudentNotFoundException;
import com.LaxmiPriya.StudentManagement.Service.StudentService;

@Controller
public class StudentViewController {

	@Autowired
	StudentService studentService;

	@GetMapping("/students")
	public String getStudents(Model model) {

		List<Student> students = studentService.getAllStudents();
		model.addAttribute("students", students);
		return "getAllStudents";

	}

	@GetMapping("/addStudent")
	public String addStudentForm(Model model) {

		model.addAttribute("student", new CreateRequestDto());
		 model.addAttribute("isUpdate", false);
		return "addStudent";
	}

	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute CreateRequestDto dto) throws Exception {

		UpdateResponseDto response = studentService.addStudent(dto);

		Long id = response.getStudent().getId();

		return "redirect:/students";
	}

	@GetMapping("/getStudent/{id}")
	public String getStudent(@PathVariable Long id, Model model) throws StudentNotFoundException {

		Student student = studentService.getStudent(id);
		model.addAttribute("student", student);
		return "getStudent";

	}

	@GetMapping("/updateStudent/{id}")
	public String editStudent(@PathVariable Long id, Model model) throws StudentNotFoundException {
		Student student = studentService.getStudent(id);
		 UpdateRequestDto dto = new UpdateRequestDto();

		    dto.setId(student.getId());   
		    dto.setName(student.getName());
		    dto.setEmail(student.getEmail());
		    dto.setCourseName(student.getCourse().getCourseName());
		    dto.setDOB(student.getDOB());
		    model.addAttribute("student", dto);
		    model.addAttribute("isUpdate", true);

		
		return "addStudent";
	}

	@PostMapping("/saveStudent/{id}")
	public String saveStudent(@PathVariable Long id, @ModelAttribute UpdateRequestDto dto) throws Exception {

		studentService.updateStudent(id, dto);

		return "redirect:/students";
	}
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable Long id,Model model)throws NotFoundException,StudentNotFoundException {
		studentService.deleteStudent(id);
		return "redirect:/students";
	}
	
	@GetMapping("findByName/{name}")
	public String findByName(@PathVariable String name,Model model) {
		List<Student> students = studentService.findByName(name);
		model.addAttribute("students", students);
		return "getStudentsByName";
	}
	

}
