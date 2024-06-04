package com.rakbank.student.mapper;

import com.rakbank.student.model.Student;
import com.rakbank.student.model.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface StudentMapper {
 
    @Mapping(source = "studentId", target = "studId")
    Student toEntity(StudentDto studentDto);

    @Mapping(source = "studId", target = "studentId")
    @Mapping( target = "createdOn", ignore = true)
    @Mapping( target = "updatedOn", ignore = true)
    StudentDto toDTO(Student student);

    List<StudentDto> toDTOList(List<Student> students);

    // Student toEntity(StudentDto studentDto);
}
