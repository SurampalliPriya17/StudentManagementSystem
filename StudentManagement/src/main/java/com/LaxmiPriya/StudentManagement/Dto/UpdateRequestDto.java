package com.LaxmiPriya.StudentManagement.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor 
public class UpdateRequestDto {

    private Long id;
    private String name;
    private String email;
    private String courseName;
    private LocalDate DOB;
}
