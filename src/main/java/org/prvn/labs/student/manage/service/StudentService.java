package org.prvn.labs.student.manage.service;

import java.util.List;
import org.prvn.labs.student.manage.domain.dto.StudentDto;

public interface StudentService {

  StudentDto registerStudent(StudentDto studentDto);

  List<StudentDto> getRegisteredStudentDetails();

  StudentDto getRegisteredStudentDetailsById(String studentId);

  StudentDto unRegisteredStudentDetailsById(String studentId);
}
