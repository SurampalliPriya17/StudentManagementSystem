package com.LaxmiPriya.StudentManagement.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.LaxmiPriya.StudentManagement.Entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {

	public List<Student> findByName(String name);

	Optional<Student> findByEmail(String email);

	@Query("SELECT s FROM Student s WHERE UPPER(s.name) = UPPER(:prefix)")
	List<Student> findByFirstnameIgnoreCase(@Param("prefix") String prefix);

}
