package org.prvn.labs.student.manage.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.prvn.labs.student.manage.domain.dto.StudentDto;
import org.prvn.labs.student.manage.domain.entity.Counter;
import org.prvn.labs.student.manage.domain.entity.Student;
import org.prvn.labs.student.manage.exception.BusinessException;
import org.prvn.labs.student.manage.mapper.ModelMapper;
import org.prvn.labs.student.manage.repository.CounterRepository;
import org.prvn.labs.student.manage.repository.StudentRepository;
import org.prvn.labs.student.manage.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Data
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;
  private final CounterRepository counterRepository;

  public StudentServiceImpl(StudentRepository studentRepository, CounterRepository counterRepository){
    this.studentRepository =  studentRepository;
    this.counterRepository = counterRepository;
  }

  @Override
  public StudentDto registerStudent(StudentDto studentDto) {
    Counter counter = null;

    Optional<Counter> studentCounter = counterRepository.findById("StudentCounter");
    if (studentCounter.isEmpty()){
     counter = counterRepository.insert(new Counter("StudentCounter",0,1));
    }

    counter = Objects.isNull(counter) ? studentCounter.get() : counter ;

    Student student = ModelMapper.MAPPER.fromStudentDtoToStudent(studentDto);
    student.setStudentId(counter.getNextSequence() );
    Student save = studentRepository.save(student);

    counter.setCurrentSequence(counter.getNextSequence());
    counter.setNextSequence(counter.getNextSequence() + 1);
    counterRepository.save(counter);

    return ModelMapper.MAPPER.fromStudentEntityToDto(save);
  }

  @Override
  public List<StudentDto> getRegisteredStudentDetails() {
    List<Student> studentList = studentRepository.findAll();
    if(studentList.isEmpty()){
      throw new BusinessException("9000","No Student Registered yet.Please register at least 1 Student","No Student record found", HttpStatus.OK);
    }
    return studentList.stream()
                                          .map(ModelMapper.MAPPER::fromStudentEntityToDto)
                                          .collect(Collectors.toList());
  }

  @Override
  public StudentDto getRegisteredStudentDetailsById(String studentId) {
    Student student = studentRepository.findByStudentId(Integer.valueOf(studentId));
    if(Objects.isNull(student)){
      throw new BusinessException("9001","No Student record found with student id : "+ studentId,"Process completed without response",HttpStatus.OK);
    }
    return ModelMapper.MAPPER.fromStudentEntityToDto(student);
  }
}
