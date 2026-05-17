package com.LaxmiPriya.StudentManagement.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LaxmiPriya.StudentManagement.Entity.StudentLog;

public interface StudentLogRepo extends JpaRepository<StudentLog,Long> {
	

}
