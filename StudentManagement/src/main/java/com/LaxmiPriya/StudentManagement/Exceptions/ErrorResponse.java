package com.LaxmiPriya.StudentManagement.Exceptions;



import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
	private String message;
   

}