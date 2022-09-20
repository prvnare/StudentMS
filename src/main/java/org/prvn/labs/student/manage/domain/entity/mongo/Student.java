package org.prvn.labs.student.manage.domain.entity.mongo;

import java.util.Date;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document
public class Student {

  @MongoId
  private String id;
  private Integer studentId;
  private String firstName;
  private String middleName;
  private String lastName;
  private Integer age;
  private String address;
  private Date dob;
  private String guardianName;
  private String fatherName;
  private String motherName;
  private String nationality;
  private String programCode;
  private String gender;
  private String adhaar;
  private String contactNumber;

}
