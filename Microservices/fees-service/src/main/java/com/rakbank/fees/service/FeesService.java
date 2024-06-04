package com.rakbank.fees.service;



import com.rakbank.fees.model.Fees;
import com.rakbank.fees.model.Response;
import com.rakbank.fees.model.student.StudentDto;
import org.springframework.http.ResponseEntity;

public interface FeesService {
    Fees saveFees(Fees student);
    ResponseEntity<Response> getReceiptDetails(Long studentId);
    ResponseEntity<StudentDto> getStudentByWebClient(Long studentId);

}