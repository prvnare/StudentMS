package org.prvn.labs.student.manage.api;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.prvn.labs.student.manage.domain.dto.StudentDto;
import org.prvn.labs.student.manage.mapper.ModelMapper;
import org.prvn.labs.student.manage.service.StudentService;
import org.prvn.labs.student.manage.ui.request.StudentRequest;
import org.prvn.labs.student.manage.ui.response.StudentResponse;
import org.prvn.labs.student.manage.validator.StudentRequestValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public ResponseEntity<StudentResponse> registerStudent(@RequestBody StudentRequest student){
    StudentRequestValidator.validate(student);
    log.info("registering the student with details : {}" , student);
    StudentDto studentDto = ModelMapper.MAPPER.fromStudentRequestToDto(student);
    studentDto = studentService.registerStudent(studentDto);
    log.info("Registration is completed with student id : {} ", studentDto.getStudentId());
    return new ResponseEntity<>(ModelMapper.MAPPER.fromStudentDtoToResponse(studentDto),HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<StudentResponse>> getStudentDetails(){
    List<StudentDto> registeredStudentDetails = studentService.getRegisteredStudentDetails();
    return  new ResponseEntity<>(ModelMapper.MAPPER.fromStudentDtoToResponse(registeredStudentDetails),HttpStatus.OK);
  }

  @GetMapping("/{studentId}")
  public ResponseEntity<StudentResponse> getStudentDetailsByStudentId(@PathVariable String studentId){
    StudentRequestValidator.validateStudentID(studentId);
    log.info("fetching the student details for student id : {}", studentId );
    StudentDto registeredStudentDetails = studentService.getRegisteredStudentDetailsById(studentId);
    log.info("student details for the student id :  {} --> {}", studentId, registeredStudentDetails);
    return  new ResponseEntity<>(ModelMapper.MAPPER.fromStudentDtoToResponse(registeredStudentDetails),HttpStatus.OK);
  }

  @DeleteMapping("/{studentId}")
  public ResponseEntity<StudentResponse> unregisterStudent(@PathVariable String studentId){
    StudentRequestValidator.validateStudentID(studentId);
    log.info("unregistering the student details for student id : {}", studentId );
    StudentDto registeredStudentDetails = studentService.unRegisteredStudentDetailsById(studentId);
    log.info(" unregistered the student details for student id :  {} --> {}", studentId, registeredStudentDetails);
    return  new ResponseEntity<>(ModelMapper.MAPPER.fromStudentDtoToResponse(registeredStudentDetails),HttpStatus.OK);
  }

}
