package com.rakbank.fees.service;

import com.rakbank.fees.exception.custom.HttpClientException;
import com.rakbank.fees.mapper.FeesMapper;
import com.rakbank.fees.model.Fees;
import com.rakbank.fees.model.Response;
import com.rakbank.fees.model.student.StudentDto;
import com.rakbank.fees.repository.FeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class FeesServiceImpl implements FeesService {

    @Autowired
    private FeesRepository feesRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    FeesMapper feesMapper;

    @Autowired
    WebClient.Builder studentWebClient;

    @Value("${student.service.url.id}")
    private String studentById;

    @Override
    public Fees saveFees(Fees fees) {
        ResponseEntity<StudentDto> studentDto = getStudentByRestTemplate(fees.getStudentId());
        fees.setDate(LocalDateTime.now());
        fees.setReferenceId(generateRandomNumber());
        return feesRepository.save(fees);
    }

    public ResponseEntity<Response> getReceiptDetails(Long studentId) {
        ResponseEntity<StudentDto> studentDto = getStudentByWebClient(studentId);
        Response response = new Response();
        List<Fees> feesList = feesRepository.findByStudentId(studentId);
        Optional<Fees> maxGradeFees = feesList.stream()
                .max(Comparator.comparing(Fees::getGrade));
        if (maxGradeFees.isEmpty()) {
            throw new HttpClientException("Payment not found");
        }
        response.setFeesDto(feesMapper.toDTO(maxGradeFees.get()));
        response.setStudentDto(studentDto.getBody());
        response.setHttpStatus(HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<StudentDto> getStudentByRestTemplate(Long studentId) {
        ResponseEntity<StudentDto> studentDto = null;
        try {
            studentDto = restTemplate.getForEntity(studentById + studentId
                    , StudentDto.class);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new HttpClientException("Student not found");
        }
        return studentDto;
    }

    public String generateRandomNumber() {
        long min = 100000000000L; // Minimum 12-digit number
        long max = 999999999999L; // Maximum 12-digit number
        long randomNumber = min + (long) (Math.random() * (max - min + 1));
        return String.valueOf(randomNumber);
    }

    public ResponseEntity<StudentDto> getStudentByWebClient(Long studentId) {
        return studentWebClient.build()
                .get()
                .uri("/api/student/v1/{id}", studentId)
                .exchange()
                .flatMap(response -> response.toEntity(StudentDto.class)).block();
    }
}
