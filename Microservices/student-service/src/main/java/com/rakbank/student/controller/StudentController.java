package com.rakbank.student.controller;
 
import com.rakbank.student.exception.custom.IdMissingException;
import com.rakbank.student.mapper.StudentMapper;
import com.rakbank.student.model.Student;
import com.rakbank.student.model.StudentDto;
import com.rakbank.student.service.StudentService;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    
    private static final Logger LOGGER = LogManager.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    @Autowired
    StudentMapper studentMapper;

    @Operation(summary = "Save Student", description = "Register a Student")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request") })
    @PostMapping("/v1")
    public ResponseEntity<StudentDto> saveStudent(@Valid @RequestBody StudentDto studentDto) {
        LOGGER.info("Student add:=========> {}", studentDto);
        Student student = studentMapper.toEntity(studentDto);
        student = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentMapper.toDTO(student));
    }


    @Operation(summary = "Update Student", description = "Update Student Details")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "204", description = "No Content") })
    @PutMapping("/v1")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto updatedStudentDto) {
        Optional<Long> studentIdOptional = Optional.ofNullable(updatedStudentDto.getId());
        if (studentIdOptional.isEmpty()) {
            throw new IdMissingException("id is missing in the Request");
        }
        return  studentService.findById(updatedStudentDto.getId())
                        .map(existingStudent -> {
                            Student student = studentService.updateStudent(studentMapper.toEntity(updatedStudentDto));
                            return ResponseEntity.ok(studentMapper.toDTO(existingStudent));
                        })
                        .orElse(new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @Operation(summary = "Get All Students", description = "Retrieve All Student Details")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "204", description = "No Content") })
    @GetMapping("/v1")
    public ResponseEntity<List<StudentDto>> findAll() {
        LOGGER.info("Student Get All Students");
        List<Student> studentList = studentService.findAll();
        return studentList.stream()
                .findFirst()
                .map(student -> new ResponseEntity<>(studentList.stream()
                        .map(studentMapper::toDTO)
                        .collect(Collectors.toList()), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get All Active Students", description = "Retrieve All Active Student Details")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "204", description = "No Content") })
    @GetMapping("/v1/active")
    public ResponseEntity<List<StudentDto>> findAllActiveStudent() {
        LOGGER.info("Student Get All Active Students");
        List<Student> studentList = studentService.findAllActive();
        return studentList.stream()
                .findFirst()
                .map(student -> new ResponseEntity<>(studentList.stream()
                        .map(studentMapper::toDTO)
                        .collect(Collectors.toList()), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get All In Active Students", description = "Retrieve All In Active Student Details")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "204", description = "No Content") })
    @GetMapping("/v1/inactive")
    public ResponseEntity<List<StudentDto>> findAllInActiveStudent() {
        LOGGER.info("Student Get All In Active Students");
        List<Student> studentList = studentService.findAllInActive();
        return studentList.stream()
                .findFirst()
                .map(student -> new ResponseEntity<>(studentList.stream()
                        .map(studentMapper::toDTO)
                        .collect(Collectors.toList()), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get Student By Id", description = "Retrieve A Student Details By Id(primary key)")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "204", description = "No Content") })
    @GetMapping("/v1/{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable("id") Long id) {
        LOGGER.info("Get Student By Id: id={}", id);
        return studentService.findById(id)
                .map(studentMapper::toDTO)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get Student By Student Id", description = "Retrieve A Student Details By Student Id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "204", description = "No Content") })
    @GetMapping("/v1/studentid/{studentid}")
    public ResponseEntity<StudentDto> findByStudentId(@PathVariable("studentid") String studentid) {
        LOGGER.info("Student Get Student By Student Id: studentid ={}", studentid);
        return studentService.findByStudentId(studentid)
                .map(studentMapper::toDTO)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Delete Student By Id", description = "Soft Delete a Student by Id, Change status to false")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "204", description = "No Content") })
    @DeleteMapping("/v1/{id}")
    public ResponseEntity<Object> deleteStudentById(@PathVariable Long id) {
        LOGGER.info("Delete Student By Id: i ={}", id);
        return studentService.existsById(id) ?
                studentService.findById(id)
                        .map(student -> {
                            studentService.deleteStudent(student);  // Update the status as false(Soft delete)
                            return ResponseEntity.status(HttpStatus.OK).build();
                        })
                        .orElse(ResponseEntity.notFound().build()) // Student not found
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Student not found
    }
}