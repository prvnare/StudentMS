package org.prvn.labs.student.manage.api;

import lombok.extern.slf4j.Slf4j;
import org.prvn.labs.student.manage.domain.dto.StudentDto;
import org.prvn.labs.student.manage.mapper.ModelMapper;
import org.prvn.labs.student.manage.service.StudentService;
import org.prvn.labs.student.manage.ui.request.StudentRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService){
    this.studentService = studentService;
  }

  @PostMapping
  public String registerStudent(@RequestBody StudentRequest student){
    log.info("registering the student with details : {}" , student);
    StudentDto studentDto = ModelMapper.MAPPER.fromStudentRequestToDto(student);
    studentService.registerStudent(studentDto);
    return "Student has been registered successfully...";
  }

}
