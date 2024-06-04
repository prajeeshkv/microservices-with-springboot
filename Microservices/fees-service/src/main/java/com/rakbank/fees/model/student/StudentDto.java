package com.rakbank.fees.model.student;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentDto{

    private Long id;
    private String firstName;
    private String lastName;
    private String studentId;
    private String mobileNumber;
    private String schoolName;
    private Boolean status;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

}

