package com.rakbank.fees.model;


import com.rakbank.fees.model.student.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private StudentDto studentDto;
    private FeesDto feesDto;
    private HttpStatus httpStatus;
}
