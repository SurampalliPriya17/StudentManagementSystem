package com.LaxmiPriya.StudentManagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.LaxmiPriya.StudentManagement.Service.StudentService;

@SpringBootTest
public class StudentServiceTest {

	@Autowired
	StudentService studentService;

	@Test
	public void test() {

		// studentService.getAllStudents().forEach(System.out::println);

		//studentService.IgnoreCase("pRiya").forEach(System.out::println);

	}

}
