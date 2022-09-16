package org.prvn.labs.student.manage.validator;


import java.util.Objects;
import org.prvn.labs.student.manage.exception.ValidationException;
import org.prvn.labs.student.manage.ui.request.StudentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Component
public class StudentRequestValidator {

  public static void validate(StudentRequest request) {
    if(Objects.isNull(request)){
        throw new ValidationException("","","", HttpStatus.BAD_REQUEST);
    }
    if(Objects.isNull(request.getFirstName()) || request.getFirstName().isEmpty() || request.getFirstName().isBlank()){
      throw new ValidationException("8000","First Name is null or empty","First Name should not be null or blank.", HttpStatus.BAD_REQUEST);
    }

    if(Objects.isNull(request.getLastName()) || request.getLastName().isEmpty() || request.getLastName().isBlank()){
      throw new ValidationException("8001","Last Name is null or empty","Last Name should not be null or blank.", HttpStatus.BAD_REQUEST);
    }

    if(Objects.isNull(request.getDob())){
      throw new ValidationException("8002","DOB is null","DOB should not be null.", HttpStatus.BAD_REQUEST);
    }

    if(Objects.isNull(request.getAdhaar()) || request.getAdhaar().isEmpty() || request.getAdhaar().isBlank()){
      throw new ValidationException("8003","Adhar is null or empty","Adhar should not be null or blank.", HttpStatus.BAD_REQUEST);
    }
  }
}
