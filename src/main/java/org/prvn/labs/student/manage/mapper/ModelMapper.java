package org.prvn.labs.student.manage.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.prvn.labs.student.manage.domain.dto.StudentDto;
import org.prvn.labs.student.manage.ui.request.StudentRequest;

@Mapper
public interface ModelMapper {

  ModelMapper MAPPER = Mappers.getMapper( ModelMapper.class );

  StudentDto fromStudentRequestToDto(StudentRequest studentDto);

  @InheritInverseConfiguration
  StudentRequest fromStudentDtoToResponse(StudentDto studentDto);
}
