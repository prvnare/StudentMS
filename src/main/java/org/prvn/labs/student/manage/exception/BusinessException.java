package org.prvn.labs.student.manage.exception;

import org.prvn.labs.student.manage.constant.AppEnum;
import org.springframework.http.HttpStatus;

public class BusinessException extends BaseException {

  public BusinessException(String errorCode, String errorDescription, String debugMessage, HttpStatus statusCode){
    super(errorCode,errorDescription,debugMessage,statusCode);
  }

  public BusinessException(AppEnum appEnum){
    super(appEnum.getErrorCode(), appEnum.getErrorDescription(), appEnum.getDebugMessage(), appEnum.getStatusCode());
  }
}
