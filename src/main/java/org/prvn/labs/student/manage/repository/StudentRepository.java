package org.prvn.labs.student.manage.repository;

import org.prvn.labs.student.manage.domain.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student,String> {

  Student findByStudentId(Integer studentId);
  Student deleteByStudentId(Integer studentId);
}
