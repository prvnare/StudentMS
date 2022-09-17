package org.prvn.labs.student.manage.validator;


import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.prvn.labs.student.manage.constant.AppEnum;
import org.prvn.labs.student.manage.exception.ValidationException;
import org.prvn.labs.student.manage.ui.request.StudentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class StudentRequestValidator {

  public static void validate(StudentRequest request) {

    if(Objects.isNull(request)){
       log.error("Registration details should not be null");
       throw new ValidationException("","","", HttpStatus.BAD_REQUEST);
    }

    if(Objects.isNull(request.getFirstName()) || request.getFirstName().isEmpty() || request.getFirstName().isBlank()){
      log.error("First name should not be null or empty");
      throw new ValidationException(AppEnum.FIRST_NAME_EMPTY_NULL);
    }

    if(Objects.isNull(request.getLastName()) || request.getLastName().isEmpty() || request.getLastName().isBlank()){
      log.error("Last name should not be null or empty");
      throw new ValidationException(AppEnum.LAST_NAME_EMPTY_NULL);
    }

    if(Objects.isNull(request.getDob())){
      log.error("DOB should not be null");
      throw new ValidationException(AppEnum.DOB_EMPTY_NULL);
    }

    if(Objects.isNull(request.getAdhaar()) || request.getAdhaar().isEmpty() || request.getAdhaar().isBlank()){
      log.error("Adhar should not be null or empty");
      throw new ValidationException(AppEnum.ADHAAR_EMPTY_NULL);
    }
  }

  public static void validateStudentID(String studentId){

    if(Objects.isNull(studentId)){
      log.error("Student Id should not be null ");
      throw new ValidationException(AppEnum.STUDENT_ID_EMPTY_NULL);
    }

    try{
      Integer.parseInt(studentId);
    }catch (Exception ex){
      log.error("Student Id should be numeric : {} ", studentId);
      throw new ValidationException(AppEnum.STUDENT_ID_NUMERIC);
    }
  }
}
