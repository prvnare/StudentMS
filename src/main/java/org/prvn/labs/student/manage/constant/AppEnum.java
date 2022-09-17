package org.prvn.labs.student.manage.constant;

import org.springframework.http.HttpStatus;


public enum AppEnum {

  FIRST_NAME_EMPTY_NULL("8001","First Name is null or empty","First Name should not be null or blank.",HttpStatus.BAD_REQUEST),
  LAST_NAME_EMPTY_NULL("8002","Last Name is null or empty","Last Name should not be null or blank.",HttpStatus.BAD_REQUEST),
  DOB_EMPTY_NULL("8003","DOB is null","DOB should not be null.",HttpStatus.BAD_REQUEST),
  ADHAAR_EMPTY_NULL("8004","Adhar is null or empty","Adhaar should not be null or blank.",HttpStatus.BAD_REQUEST),
  STUDENT_ID_EMPTY_NULL("8005","studentId is null","studentId should not be null.",HttpStatus.BAD_REQUEST),
  STUDENT_ID_NUMERIC("8006","studentId is integer","studentId should be integer",HttpStatus.BAD_REQUEST),

  NO_STUDENT_ID_FOUND_ALL("9001","No Student record found","No Student Registered yet.Please register at least 1 Student",HttpStatus.CONFLICT),
  NO_STUDENT_ID_FOUND("9002","No Student record found","Process completed without response",HttpStatus.CONFLICT),
  NO_STUDENT_ID_DELETED("9003","No Student record found","Process completed without unregister",HttpStatus.CONFLICT);


  private final String  errorCode;
  private final String errorDescription;
  private final String debugMessage;
  private final HttpStatus statusCode;


  AppEnum(String errorCode, String errorDescription, String debugMessage, HttpStatus statusCode) {
    this.errorCode = errorCode;
    this.errorDescription = errorDescription;
    this.debugMessage =  debugMessage;
    this.statusCode = statusCode;
  }

  public String getErrorCode(){
    return errorCode;
  }

  public String getErrorDescription(){
    return errorDescription;
  }

  public String getDebugMessage(){
    return debugMessage;
  }

  public HttpStatus getStatusCode(){
    return statusCode;
  }

}
