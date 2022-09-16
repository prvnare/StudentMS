package org.prvn.labs.student.manage.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends BaseException{

  public ValidationException(String errorCode, String errorDescription, String debugMessage, HttpStatus statusCode){
    super(errorCode,errorDescription,debugMessage,statusCode);
  }

}
