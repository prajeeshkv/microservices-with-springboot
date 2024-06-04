package com.rakbank.fees.controller;


import com.rakbank.fees.exception.ErrorResponse;
import com.rakbank.fees.mapper.FeesMapper;
import com.rakbank.fees.model.Fees;
import com.rakbank.fees.model.FeesDto;
import com.rakbank.fees.model.Response;
import com.rakbank.fees.model.student.StudentDto;
import com.rakbank.fees.service.FeesService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/fees")
public class FeesController {
    
    private static final Logger LOGGER = LogManager.getLogger(FeesController.class);

    @Autowired
    FeesService feesService;

    @Autowired
    FeesMapper feesMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Operation(summary = "Save Fees", description = "Register a Fees")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request") })
    @PostMapping("/v1/payment")
    public ResponseEntity<Response> saveFees(@Valid @RequestBody FeesDto feesDto) {
        LOGGER.info("Fees add: {}", feesDto);
        Fees fees = feesMapper.toEntity(feesDto);
        fees = feesService.saveFees(fees);
        return feesService.getReceiptDetails(fees.getStudentId());
    }

    @Operation(summary = "Get Receipt", description = "Get Receipt for a student and fees payment")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "204", description = "No Content") })
    @GetMapping("/v1/payment/receipt/{studentid}")
    @CircuitBreaker(name = "student-service", fallbackMethod = "handleFallBackMethod")
    @Retry(name = "student-service")
    public ResponseEntity<Response> getReceiptDetails(@PathVariable("studentid") Long studentid) {
        return feesService.getReceiptDetails(studentid);
    }

    public ResponseEntity<ErrorResponse> handleFallBackMethod(Throwable throwable) {
        List<String> errorList = new ArrayList<>();
        errorList.add("Service taking too much time to respond.........");
        ErrorResponse errorResponse = new ErrorResponse(errorList, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}