package org.prvn.labs.student.manage.service.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.prvn.labs.student.manage.domain.dto.StudentDto;
import org.prvn.labs.student.manage.service.StudentService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Data
public class StudentServiceImpl implements StudentService {

  @Override
  public StudentDto registerStudent(StudentDto studentDto) {
    System.out.println("From : --->" + studentDto);
    return null;
  }
}
