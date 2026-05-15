package com.LaxmiPriya.StudentManagement.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LaxmiPriya.StudentManagement.Entity.Course;

public interface CourseRepo extends JpaRepository<Course,Long> {
	
	Optional<Course> findByCourseName(String courseName);

}
