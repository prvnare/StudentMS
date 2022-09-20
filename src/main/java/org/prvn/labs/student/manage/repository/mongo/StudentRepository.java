package org.prvn.labs.student.manage.repository.mongo;

import org.prvn.labs.student.manage.domain.entity.mongo.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student,String> {

  Student findByStudentId(Integer studentId);
  Student deleteByStudentId(Integer studentId);
}
