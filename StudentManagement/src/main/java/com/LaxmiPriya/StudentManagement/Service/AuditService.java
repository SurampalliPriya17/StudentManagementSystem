package com.LaxmiPriya.StudentManagement.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.stereotype.Service;

import com.LaxmiPriya.StudentManagement.Entity.StudentLog;
import com.LaxmiPriya.StudentManagement.Repo.StudentLogRepo;

import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditService {

	@Autowired
	StudentLogRepo studentLogRepo;

	  @Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveLog() {
		StudentLog log=new StudentLog();

	    log.setAction("Student Created");
	    log.setCreatedAt(LocalDateTime.now());
	    
	    studentLogRepo.save(log);
		
	}
	
	

}
