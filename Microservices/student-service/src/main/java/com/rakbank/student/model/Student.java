package com.rakbank.student.model;

import com.rakbank.student.audit.AuditorEntity;
import com.rakbank.student.validator.ValidateMobileNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Data
public class Student extends AuditorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @NotNull(message = "First name cannot be null")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters.")
    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @NotNull(message = "Student Id cannot be null")
    @Pattern(regexp = "^\\d{6}$", message = "Student Id must contain exactly 6 digits.")
    @Column(name="stud_id")
    private String studId;

    @NotNull(message = "Mobile number cannot be null")
    @ValidateMobileNumber
    @Column(name="mobile_number")
    private String mobileNumber;

    @Column(name="school_name")
    private String schoolName;

    @Column(name="status")
    private Boolean status;

}
