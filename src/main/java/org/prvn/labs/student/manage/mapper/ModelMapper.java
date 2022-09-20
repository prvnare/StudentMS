package org.prvn.labs.student.manage.mapper;


import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.prvn.labs.student.manage.domain.dto.StudentDto;
import org.prvn.labs.student.manage.domain.entity.mongo.Student;
import org.prvn.labs.student.manage.ui.request.StudentRequest;
import org.prvn.labs.student.manage.ui.response.StudentResponse;

@Mapper
public interface ModelMapper {

  ModelMapper MAPPER = Mappers.getMapper( ModelMapper.class );

  @Mapping(target = "studentId", ignore = true)
  StudentDto fromStudentRequestToDto(StudentRequest studentDto);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "studentId", ignore = true)
  Student fromStudentDtoToStudent(StudentDto  dto);

  @InheritInverseConfiguration
  StudentDto fromStudentEntityToDto(Student student);

  StudentResponse fromStudentDtoToResponse(StudentDto studentDto);

  List<StudentResponse> fromStudentDtoToResponse(List<StudentDto> studentDto);
}
