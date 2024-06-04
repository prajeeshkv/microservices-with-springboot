package com.rakbank.student.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentDto{

    @Schema(hidden = true)
    private Long id;
    private String firstName;
    private String lastName;
    private String studentId;
    private String mobileNumber;

    @Schema(hidden = true)
    private String schoolName;

    @Schema(hidden = true)
    private Boolean status;

    @Schema(hidden = true)
    private LocalDateTime createdOn;

    @Schema(hidden = true)
    private LocalDateTime updatedOn;

}
