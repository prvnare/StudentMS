package org.prvn.labs.student.manage.ui.request;

import java.util.Date;
import lombok.Data;

@Data
public class StudentRequest {
  private String firstName;
  private String middleName;
  private String lastName;
  private int age;
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
