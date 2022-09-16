package org.prvn.labs.student.manage.exception.handler;

import org.prvn.labs.student.manage.exception.BaseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

  @Value("${spring.application.name}")
  private String appName;

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<Object> handleBaseException(Exception ex, ServletWebRequest request){
    BaseException exception = (BaseException)ex;
    exception.setEndpoint(request.getRequest().getRequestURI());
    exception.setServiceName(appName);
    exception.setTypeofError(exception.getClass().getSimpleName());
    exception.setOperation(request.getRequest().getMethod());
    exception.setTraceId(request.getHeader("trace-id"));
    return handleExceptionInternal(ex, exception, new HttpHeaders(), exception.getStatusCode(), request);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleException(Exception ex, ServletWebRequest request) {
    BaseException exception = new BaseException();
    exception.setEndpoint(request.getRequest().getRequestURI());
    exception.setServiceName(appName);
    exception.setTypeofError("INTERNAL_SERVER_ERROR");
    exception.setOperation(request.getRequest().getMethod());
    exception.setDebugMessage(ex.getLocalizedMessage());
    exception.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
    exception.setTraceId(request.getHeader("trace-id"));
    exception.setErrorCode("9999");
    exception.setErrorDescription("INTERNAL_SERVER_ERROR");
    return handleExceptionInternal(ex, exception, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }
}
